//package halfterm;
//
//class Node {
// int data;
// Node next;
// Node(int data) {
// this.data = data;
// this.next = null;
// }
//}
//
//class MyList {
// private Node head;
//
// public MyList() {
// this.head = null;
// }
//
// // 在链表末尾插入一个值为data的节点
// public void add(int data) {
// Node newNode = new Node(data);
// if (head == null) {
// head = newNode;
// } else {
// Node current = head;
// while (current.next != null) {
// current = current.next;
// }
// current.next = newNode;
// }
// }
//
// // 在链表中查找值为data的Node节点，并返回该节点（3分）
// public Node find(int data) {
//     Node newNode = new Node(data);
//     int flag=1;
//     if (head == null) {
//         head = newNode;
//     } else {
//     Node current = head;
//     while(current.next != newNode) {
//         current = current.next;
//         flag=1;
//         if(current.next != null) {break;}
//     }
//     flag=0;
//     }
//     if(flag==0)
//     return null;
// // 实现查找功能
// }
//
//    // 删除链表中第index位置上的节点，链表中元素的位置从0开始
//            // 考虑index超出链表中索引范围的情况（如index < 0或大于等于链表中元素个数）（4分）
//            public void deleteNodeByIndex(int index) {
//        // 实现删除功能
//    }
//
//    // 输出链表中所有Node的值（3分）
//            public void printList() {
//        // 实现打印功能
//    }
//
//    public static void main(String[] args) {
//        MyList list = new MyList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//
//        System.out.println("初始链表中的元素为：");
//        list.printList();
//
//        System.out.println("在链表中查找值为1的节点：");
//        Node foundNode = list.find(1);
//        if (foundNode != null) {
//            System.out.println("找到节点，值为：" + foundNode.data);
//        } else {
//            System.out.println("未找到节点");
//        }
//
//        System.out.println("在链表中查找值为9的节点：");
//        foundNode = list.find(9);
//        if (foundNode != null) {
//            System.out.println("找到节点，值为：" + foundNode.data);
//        } else {
//            System.out.println("未找到节点");
//        }
//
//        System.out.println("尝试删除链表中第-1个元素：");
//        list.deleteNodeByIndex(-1);
//        list.printList();
//
//        System.out.println("删除链表中第0个元素：");
//        list.deleteNodeByIndex(0);
//        list.printList();
//
//        System.out.println("删除链表中第4个元素：");
//        list.deleteNodeByIndex(4);
//        list.printList();
//    }
//}