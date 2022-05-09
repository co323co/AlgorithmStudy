import java.util.Arrays;

public class L1_폰켓몬 {
    public static void main(String[] args) {
        int[] nums = {3,1,2,3};
        System.out.println(solution(nums));
    }
    static int ans = 0;
    static public int solution(int[] nums) {
        int[] distinct = Arrays.stream(nums).distinct().toArray();
        if(distinct.length <= nums.length/2) return distinct.length;
        else return nums.length/2;
    }

//    //k는 뽑은 횟수
//    static void combination(int[] arr ,int[] sel, int k, int start) {
//        if(k==sel.length){
//            ans = Math.max(ans, (int)Arrays.stream(sel).distinct().count());
//            return;
//        }
//        for(int i=start; i<arr.length; i++){
//            sel[k] = arr[i];
//            combination(arr, sel, k+1, start+1);
//        }
//    }
}
