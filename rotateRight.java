class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;

        int n = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            n++;
        }

        k %= n;
        if (k == 0)
            return head;

        tail.next = head; // make circular list

        int stepsToNewTail = n - k;
        ListNode newTail = tail;

        while (stepsToNewTail-- > 0) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}
