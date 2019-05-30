package com.kunlinr.lifealien.spring.springjdbc;

import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_DRIVERCLASS;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_INIT_POOLSIZE;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_MAX_POOLSIZE;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_PASS;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_URL;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_USER;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTest {
	JdbcTemplate template;

	@Test
	public void testQuery() {
//		Properties props = new Properties();
//		props.setProperty(K_USER, "root");
//		props.setProperty(K_PASS, "mybatis");
//		props.setProperty(K_URL, "jdbc:mysql://localhost:3306/lifeaide");
//		props.setProperty(K_DRIVERCLASS, "com.mysql.jdbc.Driver");
//		props.setProperty(K_INIT_POOLSIZE, "5");
//		props.setProperty(K_MAX_POOLSIZE, "10");
//		if(template == null) {
//			template = DBManager.getTemplate(props);
//		}
//		int[] a = null;
//		String sql = "SELECT id, name FROM user";
//		Map<String, Object> maps = template.queryForMap(sql);
//		template.query(sql,e->{
//			System.out.println(e.getString("id"));
//		});
//		int[] nums = new int[] {0,0,1};
//		moveZeroes(nums);
//		System.out.println(nums);
//		System.out.println(maps);
		ListNode l1 = new ListNode(8);
		ListNode content = l1;
		content.next = new ListNode(9);
		content = content.next;
		content.next = new ListNode(9);
		content = content.next;
		ListNode l2 = new ListNode(2);
		addTwoNumbers(l1,l2);
	}
	
	 public void moveZeroes(int[] nums) {
	        // int temp;
	        // for(int i=0;i<nums.length-1;i++){
	        //     for(int j=0;j<nums.length-i-1;j++){
	        //         if(nums[j] == 0){
	        //             temp = nums[j];
	        //             nums[j] = nums[j+1];
	        //             nums[j+1] = temp;
	        //         }
	        //     }
	        // }
//		Stack<Integer> list = new Stack<Integer>(); 
		List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                list.add(nums[i]);
            }
        }
        
        int[] copy = nums.clone();
        int j=0;
        for(int i=0;i<copy.length;i++) {
        	if(copy[i] == 0) {
        		nums[j] = copy[i];
        		j++;
        	}
        }
        for(int i=j+1;i<copy.length;i++) {
        	nums[i] = 0;
        }
        
        for(int i = list.size();i<nums.length;i++) {
        	nums[i] = 0;
        }
//	        for(int i=0;i<nums.length-1;i++){
//	            if(nums[i] == 0){
//	                for(int j=i;j<nums.length-1;j++){
//	                    nums[j] = nums[j+1];
//	                }
//	                nums[nums.length-1] = 0;
//	            }
//	        }
	    }
	 
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        
		 int sum = l1.val + l2.val;
	        int v = sum/10;
	        ListNode re = new ListNode(sum - v*10);
	        ListNode content = re;
	        while(l1.next!=null || l2.next!=null){
	            sum = (l1.next==null?0:l1.next.val) + (l2.next==null?0:l2.next.val) + v;
	            v = sum/10;
	            content.next = new ListNode(sum - v*10);
	            l1 = l1.next;
	            l2 = l2.next;
	            content = re.next;
	        }
	        if(v != 0){
	            content.next = new ListNode(v);
	        }
	        return re;
	    }
	 
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
}
