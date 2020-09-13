package mj_robinhood;

public class Robinhood_mianjing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	/**
	 * 第一题
	 * 计算器 只应考虑 加 乘 括号
	 * 不用考虑复杂度 直接递归做的 每次有左括号就找到对应的有括号，抽出中间那段的substring递归进入下一层 很好做
	 * 第二题
	 * 和地里一样，多线程环境下的转账有啥问题，答得太菜了 语无伦次 交流一塌糊涂
	 * 
	 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=454490&highlight=robinhood
	 */
	
	/**
	 * https://www.1point3acres.com/bbs/thread-445272-1-1.html
	 * 
	 */
	
	/**
	 * 
	def bad_transfer(src_account, dst_account, amount): 
    src_cash = src_account.cash # DB read
    dst_cash = dst_account.cash # DB read
    if src_cash < amount: 
        raise InsufficientFunds
    src_account.cash = src_cash - amount # DB write
    src_account.send_src_transfer_email()
    dst_account.cash = dst_cash + amount # DB write     
 
    dst_account.send_dst_transfer_email()
	 */
	
	/**
	 * 
	 std::mutex mtx;
void bad_transaction(val):
        mtx.lock();
        remain = account1.remain
        if val > remain:
                return;
        mtx.unlock();. visit 1point3acres for more.
        mtx.lock();
        account1.withdraw(val); # db write. more info on 1point3acres
        mtx.unlock();
        send_email_to_account1();
        mtx.lock();
        account2.deposite(val);
        mtx.unlock();
        send_email_to_account2();

	 */
	
	/**
	 * https://www.postgresql.org/docs/8.3/static/tutorial-transactions.html
	 * 
	 * BEGIN;
UPDATE accounts SET balance = balance - 100.00
    WHERE name = 'Alice';
SAVEPOINT my_savepoint;
UPDATE accounts SET balance = balance + 100.00
    WHERE name = 'Bob';
-- oops ... forget that and use Wally's account
ROLLBACK TO my_savepoint;
UPDATE accounts SET balance = balance + 100.00
    WHERE name = 'Wally';
COMMIT;
	 */
	
	/**
	 * 1. 给你一个array，定义这个array的degree是array里面出现最多的element的出现次数。要求你output最短的含有array degree的subarray的长度。
	 * 比如[1,1,2,1,2,0,2]就output 4。
	 * 2. 给你一个句子和每一行的长度limit，要求你output这个句子broken into lines。每一行前后都不能有空格，每个单词不能被断开。
	 * 3. 处理两个文件，分别是holdings和trades，每个文件里面每一行都是一个有固定格式的statement，output最后有多少钱。
	 */
	
	/**
	 * onsite四轮
	 * - hiring manager聊项目，需要你介绍项目背景的具体过程，会问一些问题
	 * - 算法题。给问题，讲解体思路和分析时间空间复杂度，不需要写具体code。.
	 * - coding。电脑上写code，可以用ide。题目就是简单的模拟题。要求是写出可以提交到prod的代码，看你编程习惯，unit test之类的吧.
	 * - 系统设计。设计一个他们相关的系统。这轮答的不好。牵涉到transaction，预期是用database里的transaction的支持（他们用Postgres）。
	 *   我没往这上面靠，自己加了一堆锁试图解决问题，但自己实现transaction太难了。
	 *  感觉他们面试组织的比较好，每一轮一个侧重点。. 
	 */
	
	
	
}
