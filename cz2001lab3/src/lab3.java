import java.util.Scanner;

class lab3 {

    /* Comparison counter*/
    private static long counter;

    /* Threshold S*/
    private static final int S = 1000;

    private static long startTime1;
    private static long endTime1;
    private static long totalTime1 = 0;
    private static long startTime2;
    private static long endTime2;
    private static long totalTime2 = 0;

    public static void main(String[] args) {


        /* Range for the random integers */
        int max = 100;

        int min = 0;

        int range = max - min + 1;

        int rand;


		/* Input array size */
        System.out.println("Input number of integers for array:");

        Scanner sc = new Scanner(System.in);

        int numOfInt = sc.nextInt();

        int[] E = new int[numOfInt];

        for (int i = 0; i < numOfInt; i++) {
            rand = (int) (Math.random() * range) + min;
            E[i] = rand;
        }

        /* Create copy of original array */
		int[] E1 = new int[numOfInt];
        System.arraycopy(E, 0, E1, 0, E.length);

        /*     Print out original array */
        //   print(E, "Original Array:");

        /* Call Mergesort on original array */
        startTime1 = System.nanoTime();
        mergeSort(E, 0, E.length - 1);
        endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;

        long tempCounter = counter;
        System.out.println("---Mergesort without insertion---");
        System.out.println("Number of comparisons: " + counter);
        System.out.println("Time taken: " + totalTime1 + " nanoseconds");
        //  print(E, "Array Output:");

        /* Reset comparison counter */
        counter = 0;

        /* Call MergeInsertionSort on copy of original array */
        startTime2 = System.nanoTime();
        mergeInsertionSort(E1, 0, E1.length - 1, S);
        endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;

        System.out.println("\r\n---Mergesort with insertion---");
        System.out.println("Number of comparisons: " + counter);
        System.out.println("Time taken: " + totalTime2 + " nanoseconds");
        //  print(E1, "Array Output:");


		/* Comparison between Mergesort algorithms */
        System.out.println("\r\nFor array size of " + numOfInt + " and threshold of " + S +":");

        if (counter > tempCounter)
            System.out.println("Mergesort without insertion has less comparisons");
        else
            System.out.println("Mergesort with insertion has less comparisons");

        if (totalTime1 < totalTime2)
            System.out.println("Mergesort without insertion is faster");
        else
            System.out.println("Mergesort with insertion is faster");


    }


    private static void mergeSort(int[] E, int first, int last) {

        if (first < last) {

            int mid = (first + last) / 2;

            mergeSort(E, first, mid);
            mergeSort(E, mid + 1, last);

            merge(E, first, mid, last);
        }
    }

    private static void mergeInsertionSort(int[] E, int first, int last, int S) {

        /* If array is more than threshold */
        if ((last - first) > S) {

            int mid = (first + last) / 2;

            mergeInsertionSort(E, first, mid, S);
            mergeInsertionSort(E, mid + 1, last, S);

            merge(E, first, mid, last);

        } else {

            insertionSort(E, first, last);

        }
    }

    private static void merge(int[] E, int first, int mid, int last) {

        int i, j;

        if (last - first <= 0) {
            return;
        }

        /* Calculate size for the arrays for the two halves*/
        int a = mid - first + 1;
        int b = last - mid;

        /* Create temp arrays for left and right half */
        int[] leftHalf = new int[a];
        int[] rightHalf = new int[b];

        /* Split array into two halves*/
        for (i = 0; i < a; ++i)
            leftHalf[i] = E[first + i];
        for (j = 0; j < b; ++j)
            rightHalf[j] = E[mid + 1 + j];

        /* Initial indexes of the temp arrays*/
        i = 0;
        j = 0;

        /* Temp variable to store first index*/
        int temp = first;

        /* Compare and sort the elements*/
        while (i < a && j < b) {
            if (leftHalf[i] < rightHalf[j]) {
                E[temp] = leftHalf[i];

                i++;
            } else if (leftHalf[i] > rightHalf[j]) {
                E[temp] = rightHalf[j];

                j++;
            } else {
                E[temp] = leftHalf[i];
                i++;
                ++temp;
                E[temp] = rightHalf[j];
                j++;

            }
            counter++;
            temp++;
        }

        /*  Copy any remaining elements in left and right temp arrays back*/
        while (i < a) {
            E[temp] = leftHalf[i];
            i++;
            temp++;
        }

        while (j < b) {
            E[temp] = rightHalf[j];
            j++;
            temp++;
        }

    }

    /* Insertion sort for when subarray size is < S*/
    private static void insertionSort(int[] E, int first, int last) {

        for (int i = first; i < last; i++) {

            for (int j = i + 1; j > first; j--) {
                counter++;
                if (E[j] < E[j - 1]) {

                    int temp = E[j];
                    E[j] = E[j - 1];
                    E[j - 1] = temp;

                } else {

                    break;

                }
            }

        }


    }

    /*  Print function */
    private static void print(int[] E, String msg) {

        System.out.println(msg);

        for (int i1 : E) System.out.print(i1 + " ");
        System.out.print("\r\n");

    }

}

