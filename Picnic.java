package algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Picnic {
    public static int[][] student;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int studentNumber = Integer.parseInt(st.nextToken());
            int pairNumber = Integer.parseInt(st.nextToken());

            student = new int[studentNumber][studentNumber];
            boolean[] visit = new boolean[studentNumber];

            //2차원 인접행렬 설정하기
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int studentOne = Integer.parseInt(st.nextToken());
                int studentTwo = Integer.parseInt(st.nextToken());

                student[studentOne][studentTwo] = student[studentTwo][studentOne] = 1;
            }

            System.out.println(dfs(visit));

            testCase--;
        }
    }

    //시간복잡도 O(n)
    private static int dfs(boolean[] visit) {
        int firstMember = -1;
        //방문안한 firstMember 설정  0~studentNumber
        for (int i = 0; i < student.length; i++) {
            if (!visit[i]) {
                firstMember = i;
                break;
            }
        }
        //모든 노드를 방문했다면 1을 return
        if(firstMember == -1){
            return 1;
        }

        int ret = 0;
        //짝을 구함
        for(int otherMember = firstMember+1;otherMember < student.length ; otherMember++) {
            if (!visit[otherMember] && student[firstMember][otherMember] == 1) {
                //방문 여부 표시
                visit[otherMember] = true;
                visit[firstMember] = true;
                ret += dfs(visit);

                //처음에 설정했던 first (0,1)해제해야
                //다음 순열인 0,2로 시작 가능
                visit[firstMember] = false;
                visit[otherMember] = false;
            }
        }
        return ret;
    }
}
