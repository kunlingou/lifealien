package com.kunlinr.lifealien.collection;

import org.junit.Test;

import com.kunlinr.lifealien.collection.People;

public class ArrayTest {
	@Test
	public void TestClone() {
		People[] p1 = new People[2];
		p1[0] = new People("王",10,"男");
		p1[1] = new People("李",10,"男");
		People[] p2 = p1.clone();
		System.out.println(p1[0] == p2[0]);
		System.out.println(twoSum(new int[] {3,2,4},6));
		ListNode content;
		ListNode l1 = new ListNode(9);
		content = l1;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		
		ListNode l2 = new ListNode(9);
		content = l2;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		System.out.println(addTwoNumbers(l1,l2));
	}
	
	public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length<2) return new int[0];
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	 if(l1 == null || l2 == null) return null;
	 long v1 = getValue(l1);
	 long v2 = getValue(l2);
        String value = String.valueOf(v1 + v2);
        char[] charArray = value.toCharArray();
        ListNode result = new ListNode(Integer.parseInt(""+charArray[charArray.length-1]));
        ListNode content = result;
        for(int i=charArray.length-2;i>=0;i--) {
            try{
                content.next = new ListNode(Integer.parseInt(""+charArray[i]));
            }catch(Exception e){
                continue;
            }
        	content = content.next;
        }
        return result;
    }
    
    public long getValue(ListNode l1){
        long v1 = l1.val;
        int k = 1;
        while(l1.next != null){
        	k = k * 10;
            v1 = v1 + l1.next.val*k;
            l1 = l1.next;
        }
        return v1;
    }
    public class ListNode {
    	      int val;
    	      ListNode next;
    	      ListNode(int x) { val = x; }
    	  }
}
