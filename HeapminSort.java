class HeapSort {

    public static void heapSort(int[] arr) {

        int n = arr.length;

        // Build Min Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements
        for (int i = n - 1; i > 0; i--) {

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {

        int smallest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check left child
        if (left < n && arr[left] < arr[smallest]) {
            smallest = left;
        }

        // Check right child
        if (right < n && arr[right] < arr[smallest]) {
            smallest = right;
        }

        // If smallest is not root
        if (smallest != i) {

            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            heapify(arr, n, smallest);
        }
    }

    public static void main(String[] args) {

        int[] arr = {5, 3, 8, 4, 1, 2};

        heapSort(arr);

        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
