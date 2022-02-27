package algorithm.structure;

/**
 * Definition for singly-linked list.单链表
 *
 * @author tc
 * @date 2021/8/10
 */
public class SinglyListNode {

    public int val;
    public SinglyListNode next;

    SinglyListNode() {
    }

    SinglyListNode(int val) {
        this.val = val;
    }

    SinglyListNode(int val, SinglyListNode next) {
        this.val = val;
        this.next = next;
    }

}
