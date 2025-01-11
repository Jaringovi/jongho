import java.io.*;
import java.util.*;

public class Main {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int N,K;
    int[] arr;

    void init() throws IOException{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(input.readLine());
        }
        Arrays.sort(arr);
    }

    boolean checkVaild(long val) {
        int cnt = 0; 
        for(int i = 0; i<N; i++){
            cnt += arr[i]/val;
        }
        return cnt >= K ? true : false;
    }

    void binarySearch() {
        long s=1, e=arr[N-1], ans=1;
        while(s<=e){
            long mid = (s+e)/2;
            if(checkVaild(mid)){
                s = mid+1;
                ans = mid;
            } 
            else e = mid-1;
        }
        System.out.println(ans);
    }

    void solve() throws IOException {
        init();
        binarySearch();
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();       
    }
}