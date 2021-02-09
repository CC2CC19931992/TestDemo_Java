import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {

//    public static void main(String[] args) {
//        int a = -50%24;
//        System.out.println(simplifyPath("/a/b/c"));
//        System.out.println(simplifyPath_("/a/b/c"));
//    }

    //有效括号问题
    private boolean isValid(String s){
        Stack<Character> stack = new Stack();
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        for(char c: s.toCharArray()){
            if(c=='('||c=='['||c=='{'){
                stack.push(c);
            } else {
                if(stack.isEmpty()){
                    return false;
                }
                char head = stack.pop();
                Character value = pairs.get(head);
                if (!value.equals(c)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    //路径简化问题
    private static String simplifyPath(String path) {
        Deque<String> stack = new LinkedList();
        String[] items = path.split("/");
        for (String item : items) {
            if (item.isEmpty() || item.equals(".")){
                continue;
            }
            if (item.equals("..")) {
                //遇到..表示回到上一层，这里做出栈处理
                if (!stack.isEmpty()){
                    stack.pop();
                }
            } else {
                stack.push(item);
            }
        }
        return "/" + String.join("/", stack);
    }

    //路径简化问题
    private static String simplifyPath_(String path)
    {
        Stack<String> stack = new Stack<>();
        String[] items = path.split("/");
        for (String item : items) {
            if (item.isEmpty() || item.equals(".")) {
                continue;
            }
            if (item.equals("..")) {
                if (!stack.empty()) {
                    stack.pop();
                }
            } else {
                stack.push(item);
            }
        }
        return "/" + String.join("/", stack);
    }

    //缺失的第一个正数:给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
    //eg:[1,2,0] => 0
    //eg:[3,4,-1,1] => 2
    //eg:[7,8,9,11,12] => 1
    public static int firstMissingPositive(int[] nums) {
        HashMap<Integer,Integer> hashMapCompare = new HashMap<Integer, Integer>();
        for (int i= 0;i<nums.length+1;i++){
            hashMapCompare.put(i+1,i+1);
        }
        HashMap<Integer,Integer> hashMapNum = new HashMap<Integer, Integer>();
        for(int i= 0;i<nums.length;i++){
            if(hashMapNum.get(nums[i])==null){
                hashMapNum.put(nums[i],i);
            }
        }
        for (Map.Entry<Integer, Integer> entry :hashMapCompare.entrySet()) {
            if(hashMapNum.get(entry.getKey())==null){
                return entry.getKey();
            }
        }
        return 1;
    }

    public static int firstMissingPositive_(int[] nums){
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    //插入多少数据
    private static final int insertions = 1000000;

    //期望的误判率
    private static double fpp = 0.02;

    public static void main(String[] args) {

        //初始化一个存储string数据的布隆过滤器,默认误判率是0.03
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions, fpp);

        //用于存放所有实际存在的key，用于是否存在
        Set<String> sets = new HashSet<String>(insertions);

        //用于存放所有实际存在的key，用于取出
        List<String> lists = new ArrayList<String>(insertions);

        //插入随机字符串
        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }

        int rightNum = 0;
        int wrongNum = 0;

        for (int i = 0; i < 10000; i++) {
            // 0-10000之间，可以被100整除的数有100个（100的倍数）
            String data = i % 100 == 0 ? lists.get(i / 100) : UUID.randomUUID().toString();

            //这里用了might,看上去不是很自信，所以如果布隆过滤器判断存在了,我们还要去sets中实锤
            if (bf.mightContain(data)) {
                if (sets.contains(data)) {
                    rightNum++;
                    continue;
                }
                wrongNum++;
            }
        }

        BigDecimal percent = new BigDecimal(wrongNum).divide(new BigDecimal(9900), 2, RoundingMode.HALF_UP);
        BigDecimal bingo = new BigDecimal(9900 - wrongNum).divide(new BigDecimal(9900), 2, RoundingMode.HALF_UP);
        System.out.println("在100W个元素中，判断100个实际存在的元素，布隆过滤器认为存在的：" + rightNum);
        System.out.println("在100W个元素中，判断9900个实际不存在的元素，误认为存在的：" + wrongNum + "，命中率：" + bingo + "，误判率：" + percent);
    }



}
