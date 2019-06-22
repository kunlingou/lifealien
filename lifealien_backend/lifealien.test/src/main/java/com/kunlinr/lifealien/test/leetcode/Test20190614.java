package com.kunlinr.lifealien.test.leetcode;

import org.junit.Test;

public class Test20190614 {
	
	@Test
	public void test() {
		findMedianSortedArrays(new int[] {1,2},new int[] {3,4});
//		int i1=0,i2=0;
//		ListNode content;
//		double a =3/2.0;
//		ListNode l1 = new ListNode(1);
////		content = l1;
////		content.next = new ListNode(4);
////		content = content.next;
////		content.next = new ListNode(3);
//		ListNode l2 = new ListNode(9);
//		content = l2;
//		content.next = new ListNode(9);
////		content = content.next;
////		content.next = new ListNode(4);
//		ListNode addTwoNumbers = addTwoNumbers(l1,l2);
		System.out.println();
	}
	
	public ListNode getListNode(int[] is) {
		ListNode listNode = new ListNode(is[0]);
		return listNode;
	}
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 double cur = (nums1.length + nums2.length)/2.0-0.5;
	        double value = 0,value1=0,value2=0;
	        int i1=0,i2=0,type=1;
	        for(int i=0;i<nums1.length + nums2.length;i++){
	        	value1 = i1<nums1.length?nums1[i1]:Double.POSITIVE_INFINITY;
	        	value2 = i2<nums2.length?nums2[i2]:Double.POSITIVE_INFINITY;
	            if(value1<=value2){
	                type=1;i1++;
	            }else{
	                type=2;i2++;
	            }
	            if(cur-i<1 && cur-i>-1){
	                value = type==1?(value+value1):(value+value2);
	            }else if(cur-i<=-1){
	                break;
	            }
	        }
	        return value;
    }
	
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		 ListNode nu = new ListNode(0);
	        int carry = l1.val+l2.val;
	        ListNode result = new ListNode(carry%10);
	        ListNode cur = result;
	        while(l1.next !=null || l2.next !=null  || carry/10 != 0){   
	            l1=l1.next==null?nu:l1.next;
	            l2=l2.next==null?nu:l2.next;
	            carry = (l1.val+l2.val+carry/10);
	            cur.next = new ListNode(carry%10);
	            cur = cur.next;
	        }
	       return result;
    }
    
    public  int[] add(int a, int b){
        int v1 = (a+b)/10;
        return new int[]{v1,a+b-v1*10};
    }
  
  public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
     
}
