import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem24060 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int arr[];
    static int n;
    static int k;
    static int cnt;
    static int ans = -1;

    public static void main(String args[]) throws IOException {
        Input();
        merge_sort(arr, 0, n - 1);
        Output();
    }

    static void Input() throws IOException {
        token = new StringTokenizer(input.readLine());
        n = Integer.parseInt(token.nextToken());
        k = Integer.parseInt(token.nextToken());

        arr = new int[n];

        token = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(token.nextToken());
        }

    }

    static void merge_sort(int arr[], int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            merge_sort(arr, p, q);
            merge_sort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    static void merge(int arr[], int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int t = 0;
        int tmp[] = new int[r - p + 1];

        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }
        while (i <= q)
            tmp[t++] = arr[i++];
        while (j <= r)
            tmp[t++] = arr[j++];

        for (i = p, t = 0; i <= r; i++, t++) {
            arr[i] = tmp[t];
            cnt++;
            if (cnt == k) {
                ans = arr[i];
                break;
            }
        }

    }

    static void Output() {
        System.out.println(ans);
    }

}
