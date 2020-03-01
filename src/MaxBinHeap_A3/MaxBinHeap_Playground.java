package MaxBinHeap_A3;

public class MaxBinHeap_Playground {
    public static void main(String[] args) {
        // Add more tests as methods and call them here!!
        //TestInsert();
        //TestBuild();
        TestSort();
    }

    public static void TestInsert() {
        MaxBinHeap heap = new MaxBinHeap();
        heap.insert(79);
        heap.insert(36);
        heap.insert(75);
        heap.insert(72);
        heap.insert(66);
        heap.insert(78);
        heap.insert(63);
        heap.insert(62);
        heap.insert(67);
        heap.insert(90);
        heap.insert(30);
        heap.insert(24);
        heap.insert(10);
        heap.insert(20);
        heap.insert(32);
        heap.insert(96);
        heap.insert(85);
        heap.insert(8);
        heap.insert(19);
        heap.insert(35);
        heap.insert(77);
        heap.insert(82);
        heap.insert(86);
        printHeap(heap.getHeap(), heap.size());
    }


    public static void TestBuild() {
        // constructs a new maxbinheap, constructs an array of double,
        // passes it into build function. Then print collection and heap.
        MaxBinHeap mbh = new MaxBinHeap();
        double[] collection = new double[] { 79,36,75,72,66,78,63,62,67,90,30,24,10,20,32,96,85,8,19,35,77,82,86};
        mbh.build(collection);
        printHeapCollection(collection);
        printHeap(mbh.getHeap(), mbh.size());
    }

    public static void TestSort() {
        // constructs a new maxbinheap, constructs an array of double, passes
        // it into heapsort function. Then print collection and sorted array.
        MaxBinHeap mbh = new MaxBinHeap();
        double[] collection = new double[] { 79,36,75,72,66,78,63,62,67,90,30,24,10,20,32,96,85,8,19,35,77,82,86};
        double[] sorted = mbh.sort(collection);
        printSortCollection(collection);
        printArray(sorted);
    }

    public static double[] charsToDoubles(char[] chars) {
        double[] ret = new double[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ret[i] = charToInt(chars[i]);
        }
        return ret;
    }

    public static int charToInt(char c) {
        return c - 'a';
    }

    public static void printHeapCollection(double[] e) {
        // this will print the entirety of an array of doubles you will pass
        // to your build function.
        System.out.println("Printing Collection to pass in to build function:");
        for (int i = 0; i <  e.length; i++) {
            System.out.print(e[i] + ", ");
        }
        System.out.print("\n");
    }

    public static void printHeap(double[] e, int len) {
        // pass in mbh.getHeap(),mbh.size()... this method skips over unused 0th
        // index....
        System.out.println("Printing Heap");
        for (int i = 1; i < len + 1; i++) {
            System.out.print(e[i] + ", ");
        }
        System.out.print("\n");
    }

    public static void printSortCollection(double[] e) {
        // this will print the entirety of an array of doubles you will pass
        // to your build function.
        System.out.println("Printing Collection to pass in to heap sort function:");
        for (int i = 0; i < e.length; i++) {
            System.out.print(e[i] + ", ");
        }
        System.out.print("\n");
    }

    public static void printArray(double[] array) {
        System.out.println("Printing Array");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ". ");
        }
        System.out.print("\n");
    }
}