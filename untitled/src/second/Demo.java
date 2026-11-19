package second;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Demo {
    public static void main1(String[] args) {
    JFrame frame;
    frame=new JFrame("my window");
    frame.setSize(1000,1000);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
        JPanel panel= new JPanel();
        BorderLayout ly=new BorderLayout();
        //边框布局，分东西南北中五个位置
        panel.setLayout(ly);
        JButton btn=new JButton("zuo");
        panel.add(btn,BorderLayout.WEST);
        btn=new JButton("you");
        panel.add(btn,BorderLayout.EAST);
        btn=new JButton("shang");
        panel.add(btn,BorderLayout.NORTH);
        btn=new JButton("xia");
        panel.add(btn,BorderLayout.SOUTH);
        JTextArea txt=new JTextArea("paper");
        panel.add(txt,BorderLayout.CENTER);
//        FlowLayout ly=new FlowLayout();
//        流式布局，一行从左往右依次排列，排满换行
//        ly=new FlowLayout(FlowLayout.CENTER,10,20);
//        panel.setLayout(ly);
//        JButton btn=new JButton("ok");
//        panel.add(btn);
//        for(int i=0;i<10;++i){
//            btn=new JButton(""+i);
//            panel.add(btn);
//        }


        // GridLayout
        //网格布局，将容器均匀分成矩形网格，用于需要均匀排列
        //还有盒式布局，网格袋布局，卡片布局

    frame.setContentPane(panel);
    frame.setVisible(true);
    }
    public static void main2(String[] args){
        JPanel panel[]=new JPanel[7];
        //panel是一个容器，可以装按钮边框等一些东西。
        Border border[]=new Border[7];
        JFrame frame=new JFrame("边框示例");
        frame.setSize(350,150);
        frame.setLayout(new GridLayout(2,4));
        border[0]=new TitledBorder("title");
        border[1]=new EtchedBorder();
        border[2]=new LineBorder(Color.BLUE);
        border[3]=new MatteBorder(5,5,5,5,Color.blue);
        border[4]=new BevelBorder(BevelBorder.RAISED);
        for(int i=0;i<7;i++){
            panel[i]=new JPanel();
            panel[i].setBorder(border[i]);
            frame.add(panel[i]);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    //public class FrameText {
    public static void main3(String[] args) {
        JFrame frame = new JFrame("文本窗体");
        frame.setSize(400,300);//设置窗体的宽和高
        frame.setLayout(null);//使用绝对位置布局，不用布局管理器

        JLabel label1 = new JLabel("登录名：");//新建标签组件
        label1.setLocation(20,20);//设置标签组件位置
        label1.setSize(100,20);//设置标签组件大小
        frame.add(label1);//将标签组件添加到窗体上

        JTextField textField = new JTextField();//新建一个文本框组件
        textField.setLocation(20,40);//设置文本框的位置
        textField.setSize(150,30);//设置文本框的大小
        frame.add(textField);//将文本框添加到窗体

        JLabel label2 = new JLabel("密码：");//新建标签组件
        label2.setLocation(200,20);//设置标签组件位置
        label2.setSize(100,20);//设置标签组件大小
        frame.add(label2);//将标签组件添加到窗体上

        JPasswordField passwordField = new JPasswordField();//新建一个密码框组件
        passwordField.setLocation(200,40);//设置密码框的位置
        passwordField.setSize(150,30);//设置密码框的大小
        frame.add(passwordField);//将密码框添加到窗体

        JLabel label3 = new JLabel("文本区域：");//新建标签组件
        label3.setLocation(20,70);//设置标签组件位置
        label3.setSize(100,20);//设置标签组件大小
        frame.add(label3);//将标签组件添加到窗体上

        JTextArea textArea1 = new JTextArea();//新建一个文本区域组件
        textArea1.setLocation(20,100);//设置文本区域的位置
        textArea1.setSize(150,150);//设置文本区域的大小
        frame.add(textArea1);//将文本区域添加到窗体

        JLabel label4 = new JLabel("滚动文本域：");//新建标签组件
        label4.setLocation(200,70);//设置标签组件位置
        label4.setSize(100,20);//设置标签组件大小
        frame.add(label4);//将标签组件添加到窗体上

        JTextArea textArea2 = new JTextArea();//新建一个文本区域组件
        //新建一个滚动面板，将文本区域放到滚动面板中，垂直滚动条总是显示，水平滚动条只有在需要时显示
        JScrollPane panel = new JScrollPane(textArea2,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setLocation(200,100);//设置滚动面板的位置
        panel.setSize(150,150);//设置滚动面板的大小
        JRadioButton buttonRadio1=new JRadioButton("男");
        JRadioButton buttonRadio2=new JRadioButton("女");
        buttonRadio2.setSelected(true);
//        ButtonGroup group=new ButtonGroup("爱好");
//        group.add(buttonRadio1);
//        group.add(buttonRadio2);
        frame.add(panel);//	将滚动区域添加到窗体
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//设置窗体可见

        //待解决：为啥不显示那个按钮

    }
    public static void main4(String[] args) {
        String[] counties = {"中国","美国","俄罗斯","新加坡","英国","法国","德国","意套利"};
        String[] cities = {"北京","上海","广州","深圳","成都","武汉","南京","天津"};
        JFrame frame = new JFrame("列表窗体");
        frame.setSize(400,300);//设置窗体的宽和高
        frame.setLayout(null);//使用绝对位置布局，不用布局管理器

        JLabel label1 = new JLabel("国家：");//新建标签组件
        label1.setLocation(20,20);//设置标签组件位置
        label1.setSize(100,20);//设置标签组件大小
        frame.add(label1);//将标签组件添加到窗体上

        JComboBox<String> comboBox = new JComboBox<String>();//新建一个下拉列表组件
        for(int i = 0;i<counties.length;i++) {//向下拉列表选项中添加组件
            comboBox.addItem(counties[i]);
        }
        comboBox.setLocation(20,40);//设置下拉列表组件的位置
        comboBox.setSize(150,30);//设置下拉列表组件的大小
        comboBox.setEditable(true);//设置下拉列表组件为可编辑
        frame.add(comboBox);//将下拉列表组件添加到窗体

        JLabel label2 = new JLabel("城市：");//新建标签组件
        label2.setLocation(200,20);//设置标签组件位置
        label2.setSize(100,20);//设置标签组件大小
        frame.add(label2);//将标签组件添加到窗体上

        JList<String> list = new JList<String>(cities);//新建列表组件
        list.setLocation(200,40);//设置列表组件的位置
        list.setSize(150,200);//设置列表组件的大小
        frame.add(list);//将列表组件添加到窗体

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//设置窗体可见
    }
    public static void main(String[] args) {
        String[] tabNames = {
                "balck","green","blue",
                "red","yellow","pink"};
        Color[] colorNames = {
                Color.BLACK,Color.GREEN,Color.BLUE,
                Color.RED,Color.YELLOW,Color.PINK};
        JFrame frame = new JFrame("示例窗体");
        //按默认方式创建页签面板
//        JTabbedPane tabs = new JTabbedPane();
        //按定制方式创建页签面板
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT,
        		JTabbedPane.SCROLL_TAB_LAYOUT);
        for(int i = 0 ;i<6;i++) {
            JPanel panel = new JPanel();
            panel.setBackground(colorNames[i]);
            tabs.addTab(tabNames[i], panel);
        }
        frame.add(tabs);
        //设置窗体关闭行为，当用户点击窗体的关闭图标时，结束程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250,250);//设置窗体的宽和高
        frame.setVisible(true);//设置窗体可见
    }

}




