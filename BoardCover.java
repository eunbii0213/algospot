package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoardCover {
    public static int h;
    public static int w;
    public static int count;
    public static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            board = new boolean[h][w];

            //board초기화
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine(), "#.", true);
                for (int j = 0; j < w; j++) {
                    String str = st.nextToken();
                    if (str.equals("#")) {
                        board[i][j] = true;
                    }
                }
            }
            count = 0;
            dfs(board);
            System.out.println(count);
            t--;
        }
    }

    //O(n^2)
    private static void dfs(boolean[][] board) {
        int rowIndex = 0;
        int colIndex = 0;
        boolean startDfs = false;

        Loop1:
        for (rowIndex = 0; rowIndex < h; rowIndex++) {
            for (colIndex = 0; colIndex < w; colIndex++) {
                if (!board[rowIndex][colIndex]) {
                    startDfs = true;
                    break Loop1;
                }
            }
        }

        if (!startDfs) {
            count++;
            return;
        }
        if (rowIndex + 1 < h && colIndex - 1 < w && colIndex - 1 >= 0)
            if (!board[rowIndex][colIndex] && !board[rowIndex + 1][colIndex] && !board[rowIndex + 1][colIndex - 1]) {
                board[rowIndex][colIndex] = board[rowIndex + 1][colIndex] = board[rowIndex + 1][colIndex - 1] = true;
                dfs(board);
                board[rowIndex][colIndex] = board[rowIndex + 1][colIndex] = board[rowIndex + 1][colIndex - 1] = false;
            }
        if (rowIndex + 1 < h && colIndex + 1 < w) {
            if (!board[rowIndex][colIndex] && !board[rowIndex + 1][colIndex] && !board[rowIndex][colIndex + 1]) {
                board[rowIndex][colIndex] = board[rowIndex + 1][colIndex] = board[rowIndex][colIndex + 1] = true;
                dfs(board);
                board[rowIndex][colIndex] = board[rowIndex + 1][colIndex] = board[rowIndex][colIndex + 1] = false;
            }
            if (!board[rowIndex][colIndex] && !board[rowIndex + 1][colIndex] && !board[rowIndex + 1][colIndex + 1]) {
                board[rowIndex][colIndex] = board[rowIndex + 1][colIndex] = board[rowIndex + 1][colIndex + 1] = true;
                dfs(board);
                board[rowIndex][colIndex] = board[rowIndex + 1][colIndex] = board[rowIndex + 1][colIndex + 1] = false;
            }
            if (!board[rowIndex][colIndex] && !board[rowIndex][colIndex + 1] && !board[rowIndex + 1][colIndex + 1]) {
                board[rowIndex][colIndex] = board[rowIndex][colIndex + 1] = board[rowIndex + 1][colIndex + 1] = true;
                dfs(board);
                board[rowIndex][colIndex] = board[rowIndex][colIndex + 1] = board[rowIndex + 1][colIndex + 1] = false;
            }
        }
    }
}
