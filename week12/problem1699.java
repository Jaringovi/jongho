package jongho.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1699 {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokens;
    int dp[][];
    int n,root,ans;

    void init() throws IOException {
        n = Integer.parseInt(input.readLine());
        root = (int) Math.sqrt(n);
        dp = new int[root+1][n+1];
        for(int i = 1; i<=n; i++){
            dp[1][i] = i;
        }
    }

    void bottomUp(){
        for(int i = 2; i<=root; i++){
            for (int j = 1; j <= n; j++) {
                int crnt = (int) Math.pow(i, 2);
                if(crnt <= j){
                    dp[i][j] = Math.min(1+dp[i][j-crnt],dp[i-1][j]);
                }
                else dp[i][j] = dp[i-1][j];
            }
        }
    }

    void Solve() throws IOException {
        init();
        if((int)Math.pow(root,2) == n){
            ans = 1;
        }
        else {
            bottomUp();
            ans = Integer.MAX_VALUE;
            for(int i = 1; i<=root; i++){
                ans = Math.min(ans, dp[i][n]);
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new problem1699().Solve();
    }
}
