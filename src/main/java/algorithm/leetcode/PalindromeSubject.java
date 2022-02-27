package algorithm.leetcode;

import algorithm.structure.SinglyListNode;

/**
 * 回文相关题目
 *
 * @author tc
 * @date 2021/8/10
 */
public class PalindromeSubject {

    //region leetCode 234.请判断一个链表是否为回文链表。
    /**
     * 左侧指针
     */
    SinglyListNode left;

    //普通递归判断。后序遍历链表
    public boolean isPalindrome(SinglyListNode head) {
        left = head;
        return traverse(head);
    }

    //穿过
    boolean traverse(SinglyListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }


    //双指针法找到链表中间节点
    boolean testTowPoint(SinglyListNode head){
        SinglyListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步
        if (fast != null){
            slow = slow.next;
        }
        SinglyListNode left = head;
        SinglyListNode right = reverse(slow);

        while (right != null) {
            if (left.val != right.val){
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    //翻转链表
    SinglyListNode reverse(SinglyListNode head) {
        SinglyListNode pre = null, cur = head;
        while (cur != null) {
            SinglyListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
    //endregion
}
