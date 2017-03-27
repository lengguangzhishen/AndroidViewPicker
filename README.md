# AndroidViewPicker
the application of IOC in Android

for details in 

the usage of fragment:
  1. init
  
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ViewPicker.bindFragment(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewPicker.injectFragment(this, view);
    }
    
  2. ContentView
  
    @ContentView(R.layout.activity_main)
    public class MyFragment extends BaseFragment {}
    
  3. ViewInject
  
    @ViewInject(R.id.tv)
    TextView tv;
    
  4. Event
  
    @OnClick({R.id.tv, R.id.btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv:
                Toast.makeText(getActivity(), "tvclick", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn:
                Toast.makeText(getActivity(), "btnclick", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    
the usage of activity:

  1. init
  
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        ViewPicker.bindActivity(this);
        super.onCreate(savedInstanceState, persistentState);
    }
    
  2. ContentView
  
    @ContentView(R.layout.activity_main)
    public class MainActivity extends BaseActivity {}
    
  3. ViewInject
  
    @ViewInject(R.id.tv)
    TextView tv;
    @ViewInject(R.id.btn)
    Button btn;
    
  4. Event
  
    @OnClick({R.id.tv, R.id.btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv:
                Toast.makeText(MainActivity.this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn:
                Toast.makeText(MainActivity.this, btn.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }

