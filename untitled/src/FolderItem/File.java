package FolderItem;

public class File extends FolderItem{
    public File(String name){
        super(name);
    }
    private String extension;
    private int size;
    public void printAll(){
        System.out.println(name);
    }
}
