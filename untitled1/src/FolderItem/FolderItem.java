package FolderItem;

public class FolderItem {
    protected String name;

    public FolderItem(String name) {
        this.name=name;
    }

    /**
     * 打印自己。level 表示缩进层级：Folder 打印子项时会把 level+1 传进来。
     * 原来这里写成无参的 printAll()，而 Folder 里调用的是 printAll(level+1)，
     * 编译期找不到匹配的方法，所以补上带层级的版本（与上课演示代码一致）。
     */
    public void printAll(int level) {
    }
}
