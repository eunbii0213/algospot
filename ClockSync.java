import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //public static String[] button = new String[10];
    static final String[] button = {"xxx.............", "...x...x.x.x....", "....x.....x...xx", "x...xxxx........", "......xxx.x.x...", "x.x...........xx", "...x..........xx", "....xx.x......xx", ".xxxxx..........", "...xxx...x...x.."};

    public static void main(String[] args) throws IOException {
//        button[0] = "0 1 2";
//        button[1] = "3 7 9 11";
//        button[2] = "4 10 14 15";
//        button[3] = "0 4 5 6 7";
//        button[4] = "6 7 8 10 12";
//        button[5] = "0 2 14 15";
//        button[6] = "3 14 15";
//        button[7] = "4 5 7 14 15";
//        button[8] = "1 2 3 4 5";
//        button[9] = "3 4 5 9 13";

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] clock = new int[16];
            for (int i = 0; i < 16; i++) {
                clock[i] = Integer.parseInt(st.nextToken());
            }
            int cal = re(clock, 0);
            if (cal == 99999999) {
                cal = -1;
            }
            sb.append(cal + "\n");
            t--;
        }
        System.out.println(sb);
    }

    //스위치는 10개 있고 각 3번을 최대로 누를 수 있다 (4번 이상 누르면 원점이므로 취급하지 않음)
    //경우의 수는 총 4개. (0번 누를 때, 1번 누를 때, 2번 누를 때, 3번 누를 때)
    //만약 스위치를 30번 눌렀는데도 모두 12시를 가리키고 있지 않다면 -1를 return
    // 0번 스위치 0번 누를 때부터 시작해서 recursion으로 1번 스위치 0번 누를때...구함 >> 모든 경우의 수 탐색됨
    private static int re(int[] clock, int count) {
        boolean startRe = false;

        Loop1:
        for (int i = 0; i < 16; i++) {
            if (clock[i] != 12) {
                startRe = true;
                break Loop1;
            }
        }

        if (!startRe) {
            return 0;
        }

        //30번 스위치 누른 경우 (10개의 스위치를 각3번 누른 경우)
        if (count == 10) {
            if (startRe) {
                return 99999999;
            }
        }

        int ret = 99999999;
        for (int i = 0; i < 4; i++) {
            ret = Math.min(ret, i + re(clock, count + 1));

//            String[] tempButton = button[count].split(" ");
//
//            for (int z = 0; z < tempButton.length; z++) {
//                int index = Integer.parseInt(tempButton[z]);
//                clock[index] += 3;
//                if (clock[index] > 12) {
//                    clock[index] -= 12;
//                }
//            }

            for (int z = 0; z < 16; z++) {
                if (button[count].charAt(z) == 'x') {
                    clock[z] += 3;
                    if (clock[z] > 12) {
                        clock[z] -= 12;
                    }
                }
            }
            //StringTokenizer은 split()보다 느리다. (매번 내부 정규식을 돌리기 때문)

//            StringTokenizer st = new StringTokenizer(button[count]);
//            while (st.hasMoreTokens()) {
//                int index = Integer.parseInt(st.nextToken());
//                clock[index] += 3;
//                //12 - > 15되는 경우 방지
//                if (clock[index] > 12) {
//                    clock[index] -= 12;
//                }
//            }
        }
        return ret;
    }
}
