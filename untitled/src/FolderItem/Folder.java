package FolderItem;

import java.util.ArrayList;

public class Folder extends FolderItem{
    private ArrayList<FolderItem> folderItems;
    public Folder(String name){
        super(name);
        folderItems = new ArrayList<FolderItem>();
    }
    public void addFolderItem(FolderItem item){
        folderItems.add(item);
    }
    public void removeFolderItem(FolderItem item){
        folderItems.remove(item);
    }
    public FolderItem getFolderItem(int index){
        return folderItems.get(index);
    }
    public int getNumberOfFolderItem(){
        return folderItems.size();
    }

    public void printAll(int level){
        for(int i=0;i<level;++i){
            System.out.print("--------");;
        }
        System.out.println(name);
        for(FolderItem item:folderItems){
            item.printAll(level+1);
        }
    }
}
