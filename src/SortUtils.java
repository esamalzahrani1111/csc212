public class SortUtils {

    // Merge Sort: Splits and sorts the arrays in descending order
    public static void mergeSort(int[] docIds, int[] docScores, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively split the array into halves
            mergeSort(docIds, docScores, left, mid);
            mergeSort(docIds, docScores, mid + 1, right);

            // Merge the sorted halves
            merge(docIds, docScores, left, mid, right);
        }
    }

    // Merge: Helper function to merge two sorted arrays
    private static void merge(int[] docIds, int[] docScores, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays to hold split data
        int[] leftIds = new int[n1];
        int[] leftScores = new int[n1];
        int[] rightIds = new int[n2];
        int[] rightScores = new int[n2];

        // Copy data into the temporary arrays
        for (int i = 0; i < n1; i++) {
            leftIds[i] = docIds[left + i];
            leftScores[i] = docScores[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightIds[j] = docIds[mid + 1 + j];
            rightScores[j] = docScores[mid + 1 + j];
        }

        // Merge the temporary arrays back into the main array
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftScores[i] >= rightScores[j]) { // Sort in descending order
                docIds[k] = leftIds[i];
                docScores[k] = leftScores[i];
                i++;
            } else {
                docIds[k] = rightIds[j];
                docScores[k] = rightScores[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from the left array
        while (i < n1) {
            docIds[k] = leftIds[i];
            docScores[k] = leftScores[i];
            i++;
            k++;
        }

        // Copy any remaining elements from the right array
        while (j < n2) {
            docIds[k] = rightIds[j];
            docScores[k] = rightScores[j];
            j++;
            k++;
        }
    }
}
