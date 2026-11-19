package halfterm;

public class Sort {



    public static void main(String[] args) {

        int[] arr= {1,5,3,9,6,8,22,56,81,55};

        sort(arr);

        print(arr);



    }

    public static void sort(int[] arr) {
            int i = 0;
            for (i = 0; i < 9; i++) {
                int j = 0;
                for (j = 0; j < 9 -i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int tmp=arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = tmp;
                    }
                }
            }
        }


    public static void print(int[] arr) {
        int k=0;
        for(k=0;k<10;k++){
            System.out.println("arr[k]");
        }



    }

}
