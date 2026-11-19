package FolderItem;

public class File extends FolderItem{
    public File(String name){
        super(name);
    }
    private String extension;
    private int size;

    @Override
    public void printAll(int level){
        for(int i=0;i<level;++i){
            System.out.print("----");
        }
        System.out.println(name);
    }
}
