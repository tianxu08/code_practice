package mj_whatsapp;

public class MianJing0718 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * 数据结构问了hashmap: 为什么要用hashmap？ hashmap每次查找都是o(1)吗？java是如何处理hashmap的？为什么平均复杂度是O(1)？ 
	 * 筒子们，答案都在CC150上，所以也别忽略基础内容。 算法题就是LRU cache。
	 * 链接: https://instant.1point3acres.com/thread/192789
	 * 来源: 一亩三分地
	 */
	/**
	 * coding题是3sum，follow up是多线程加速
	 * 3sum不是转化成N个2sum做么 把2sum丢到新的thread去做
	 * 链接: https://instant.1point3acres.com/thread/192832
	 * 来源: 一亩三分地
	 */
	
	/**
	 * Given a string with alpha-numeric characters and parentheses, 
	 * return a string with balanced parentheses by removing the fewest characters possible
	 * 链接: https://instant.1point3acres.com/thread/190980
	 * 来源: 一亩三分地
	 */
	
	/*
	 * Given a string with alpha-numeric characters and parentheses, return a string with balanced parentheses by removing the fewest characters possible
链接: https://instant.1point3acres.com/thread/190980
来源: 一亩三分地
	 */
	
	/**
	 * 第一个是问在文件读写的过程操作系统都干了什么。问的挺细，怎么查找文件，buffer怎么用，thread怎么用 第二个和Google那个找图片的题很像。给一个文件系统，让写一个所有文件的iterator，用stack就行。面试官感觉是写cpp的，对一些java method不熟悉 最后问了下他whatsapp会不会出video call的功能，他说马上就出了，争取取代FaceTime～ 

链接: https://instant.1point3acres.com/thread/188263
来源: 一亩三分地
	 */
	
	
	/**
	 * 实现word trie，insert和delete，我写的太快了，20min写完，继续follow up说delete one pass O(n)。然后尝试递归，没写完时间到了

链接: https://instant.1point3acres.com/thread/184534
来源: 一亩三分地
	 */
	/**
	 * 题目是leetcode上easy的题Add Binary，就一位一位加就行，做完问了问复杂度，time space都O(N) 

链接: https://instant.1point3acres.com/thread/184246
来源: 一亩三分地
	 */
	
	/**
	 * 1. 输入一个url之后会发生什么，然后深入问了各种server的问题。详细问了load balancer中各种request distribution algorithm，以及时间复杂度，各个server是否需要entire copy of database。 2. 如何在不需要第三个变量的情况下，交换两个integer的值，给出数学算法和位运算。 3. 如何在一个sorted array中找到target value， 时间复杂度怎样 4. 如何用O(1)时间从一个unsorted array中delete一个element，string的length怎么更新？ 5. 如何不使用loop来print一个string 

链接: https://instant.1point3acres.com/thread/161588
来源: 一亩三分地
	 */
	
	
	/**
	 * 题目是让你实现 BSP(Binary Spce Partition) tree.- 已知的是每个node的class已经弄好了 - 点的class也不需要自己写 我反正没听过这个东西, 当场傻傻的问了考官那个是啥, 他给我解释了一番然后要我implement. 给你public interface void buildBSPTree(Points* pts, int len, BSPNode** node) 

链接: https://instant.1point3acres.com/thread/158185
来源: 一亩三分地
	 */
	
	/**
	 * 1. 把一个sorted list转成balanced binary search tree 2. 把sorted list转成complete binary search tree onsite第一轮: 主要讨论project。然后要实现Trie的插入。由于我说我会Haskell, 然后被要求用Haskell实现。由于之前没在白板上写过code,写得很乱。最后问hr,也说到我的code不够clean,感觉应该是因为这个悲剧了。 onsite第二轮: 也是主要讨论project。然后问了几个关于链表的题: 1. 给一个linked list, 求长度 2. 实现在linked list中append一个节点 3. 实现在linked list中prepend一个节点 4. 利用上面的函数，实现将一个数组变成linked list. 5. 要求提高4的性能。4已经是O(n), 所以要提高性能只能并发。我先是用fortress写了一个实现。利用Fortress的并发特性，可以达到O(lgn)。然后被要求不用Fortress。我又用openmp给了一个多线程实现。 

链接: https://instant.1point3acres.com/thread/119677
来源: 一亩三分地
	 */
	
	/**
	 * Huffman Tree (Priority Tree)
	 * 子，一上来问我进程通信，我说我用macbook以后问在mac下面如何通过进程名称kill进程，
	 * 如何查看进程信息，top里进程的每个属性是什么意思，OS如何管理进程，top命令是从哪里拿到进程信息的。。。
	 * Blabla，然后又开始面网络原理，怎么访问facebook.com，发的数据包是什么格式，用什么system call发数据，
	 * TCP三次握手，C++socket编程那些系统函数调用顺序blabla，还问建立socket的时候用到什么参数。。。
	 * 靠着两年前模糊的印象和迅速google撑了过去，感觉两腿发软。。。 问了大概25分钟之后开始technical interview, 
	 * 一个简单的在电话本里二分搜索，查找新联系人插入位置，如果电话本里有50 Billion个名字怎么办，
	 * 链接: https://instant.1point3acres.com/thread/153108
	 * 来源: 一亩三分地
	 */
	
	/**
	 * 果然和地里报过的面经一样，还是比较非常规的，
	 * 先是邮件发题目45min完成一个leetcode上sort list原题，
	 * 然后是第一轮skype语音面试，上过OS的课没，file system懂吗，
	 * UDP TCP 区别，HTTP response的header里都有啥，
	 * database懂吗 transaction为啥重要，多线程用过吗。
	 * 然后写了一个很简单的linkedlist的题目，
	 * 求length，append 到末尾，append到开头，append整个序列，这个速度是linear，
	 * 问你如果继续提高速度，要用到multithread，大概讲一下怎么实现这个，
	 * tips 在每个线程返回结果给master thread的时候merge的时候为了constant time，
	 * 需要记录tail的node。 然后就结束了，在等下一轮skype。
	 * 
	 * 链接: https://instant.1point3acres.com/thread/120607
	 * 来源: 一亩三分地
	 */
	
	/**
	 * 电话面试
	 电话面试第一轮: 实现一个 类似 “ls -R”的iterator功能。该iterator功能要被俩个函数体现：next()和hasnext()。
	电话第二轮：纯聊过去的项目

		电话第三轮：给一个排好序的数组，变成一个balanced bst


		Onsite面试
		linked list里detect loop，求loop的size
		给一个类似tree的graph,一个node可能有多个父节点，做deep clone，很多connections

		来到后台系统，要求设计一个基于hashing的load balancer, 要求在改变hash 函数时同一个connection里不能有out of order 的messages

		还有一个题目忘了。
	 */
	
	
	
	
	
	

}
