// 메모리: 14436kb	 시간: 108ms

import java.util.*;
import java.io.*;

public class Main {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int N,M,L;
    int arr[];

    void init() throws IOException {
        tokens = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());

        arr = new int[N];
        tokens = new StringTokenizer(bf.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(tokens.nextToken());
        }
        Arrays.sort(arr);
    }

    boolean canDivide(int distance) {
        int count = 0;
        if(N>0)
            count += (arr[0]-0-1)/ distance;
        else
            count += (L-1) / distance;

        for(int i = 1; i<N; i++){
            count += (arr[i]-arr[i-1]-1) / distance;
        }

        if(N>0)
            count += (L - arr[N-1]-1)/distance;
        
        return count <= M;
    }

    void solve() throws IOException {
        init();

        int left = 1;
        int right = L;
        int answer = L;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(canDivide(mid)){
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }
}