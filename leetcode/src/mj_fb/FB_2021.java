package mj_fb;

public class FB_2021 {
    /**
     *
     * Day1:
     *
     * 第一轮 Coding：医舞疤，留儿儿 158,622
     *
     * 第二轮 Coding：医医医 （follow up：在什么情况下应该用BFS解？如果已知是balance tree适合BFS），医舞漆灵 （follow up：如果一个vector的元素远小于另一个vector，怎么优化？可以考虑binary search元素多的vector）
     * 111 1570
     *
     *
     * Day2:
     *
     * 第三轮 Behavior：问题比较多，记不太清楚。最满意的project，如何lead project，曾今remanager feedback，等等。顺带最后5分钟做了个题: match parentheses
     * 第四轮 Coding：医似留叁，舞似疤。很惭愧这一轮两道题都没有很完美的解出来。但幸运的是，这一轮是training，面试结果好像没关系。。
     * 1463 548
     *
     * 第五轮 system design：面试官套了个马甲，本质是Yelp。早些时候看其他面经的时候，貌似大家都在讨论应该用Quadtree还是Geohash。我自己在面试用的是Geohash解法。
     *
     * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=706325&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
     *
     */

    /**
     * Facebook Onsite 面试从上午10点左右开始,报到之后有一个recruiter带着进office,总共进行4轮面试,前两轮是programming interview，第三轮是lunch，第四轮是behavioral/cultural/programming interview。
     *
     * 第一轮，一个很nice的小哥
     * 第一个问题是给一个数字序列，把里面的0放到最右边，非0的放到左边
     * 第二个问题是求一个数组中的第k大元素，九章曾发过此题的微信题解，用快速排序的思想每次只搜索一边可以在O(n)时间内解决
     *
     * 第二轮是一个印度小哥
     * 问了一个LintCode原题 add and search word，设计算法支持两个操作，一个是add一个字符串，一个是search某个字符串是否在目前的字典中。查找的串可能包含通配符‘.'，匹配任意一个字符。用Trie Tree即可。
     * 然后小哥问了一下如何在space和time之间trade off
     *
     * 第三轮是和recruiter吃个饭
     * 到什么问题可以在这时候提出，并不会影响面试结果。
     *
     * 第四轮是一个不知道哪里的小哥，说话声音很小，让人感觉和蔼可亲
     * 首先问了why facebook，以及一些做过的project，然后重点关注在project里遇到的challenge，有没有和teammate的意见不同的情况，怎么解决之类。
     * 先问了一个特别简单的动态规划题，然后是一道算法题判断一棵树是否是BST，这次写完代码有一个小错误，小哥给了一个样例，在试着跑这个样例的时候我发现并改正了bug。
     *
     * 总的来说Facebook面试的题目还是比较简单的，相比题目的难度，更加注重的是分析过程。
     * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=706985&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
     *
     */

    /**
     *  去年的盎赛面试。回馈地理
     *    两轮扣定 一轮BQ, 一轮系统（爬虫），一轮ML设计（滥用），shadow 是扣定
     *
     *    扣定六题有一体新的，两题接近李口hard的，感觉越来越难了。大家加油
     *
     *    耳伞的变种，散散，幺舅舅，留疤凌，二灵另变种但是问的是如果允许一个海翻转成陆地，找最大的岛。
     *    一体没看过，但是是树的问题，而且是从树叶往上更新。我觉得不难。
     */

    /**
     *

     本帖最后由 匿名 于 2021-1-22 06:58 编辑


     刚刚结束的5轮Facebook VO

     1. Coding: 刷题网二六和二九七

     2. Coding: 刷题网六九五和九五八

     3. BQ + Coding: 如何和不同性格的组员合作，如何处理和manager的conflict，proudest project，how to measure success of your project。刷题网二十

     4. Design: realtime aggregation system。假设我们要统计汇总每个广告的点击量以及其他的数据。输入是从client来的很多log，每个log包括(ad_id, user_id)，输出是给dashboard提供汇总数据。一些基本的要求
     存储2年的数据
     每天会有200B个log，并且会有peak
     有50M不同的ad_id
     可以接受30s的latency
     我的设计大概就是一堆api servers用来接收从各种client传来的log数据，这些api servers全都在load balancer之后。然后api servers把数据放进不同的kafka topic，每个topic负责一部分ad_id。kafka的另一头一方面接入raw log store去存储所有full history raw log，另一方面接入Hadoop cluster用map-reduce去做micro batch每30秒统计过去30秒内的数据。统计完的数据一方面放进data store，用来存储过去2年的数据，另一方面放进cache用来存储过去一小段时间的数据（比如过去10天），因为最近这段时间的数据更常被访问。最后dashboard就从两个地方读取数据。面试官要求计算大概需要多少api servers。200B这个量级着实有点大，可能因为只是log数量的缘故，算一下每秒要处理230K个log。面试官提示说可以假设每台机子每秒可以处理100K个log，我就说最少需要3个，为了redundancy需要再多一两个来handle peak。

     5. Coding: 刷题网三四和三一

     希望对大家有帮助，也欢迎讨论设计题
     https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=707907&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline

     */


    /**
     * 1. 白人小哥。散拔起。followup: no loop through dictionary. 幺儿斯久。followup: no stacks. 两个followup都没答好
     * 2. system design. Autocomplete system.
     * 3. 久死刘。幺舅舅。followup。dfs
     * 4. BQ
     * 5. ML design。abusive comments
     *
     * feedback
     * 说coding mixed。bq很好（lz感觉这轮最差），ml meet bar，system design不达标（not scalable）。两道design 题都是准备时候没见过的，所以随缘吧。
     *
     * timeline
     * 面完3天后，说feedback都有了。送manager review。第二天电话说过了，但是降级E4。然后转天周一通知，candidate review没过，不能给offer。
     *
     * 这是lz第二次遇见这情况了。两年前也是面MLE，target E4，过了manager review，没过candidate review。不知地里有没有fb ml em 来讲一讲这神秘的candidate review为啥会veto first round decision。
     * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=709207&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
     */

    /**
     * 10月recruiter 联系上  通过了Engineer manager 的screening 说反馈很好 给我讲了面试流程 包括 job talk, 两轮ai discussion, 一轮coding, 一轮ml design, 一轮behavior。 于是准备了三周，因为没有看到ai discussion的经验 所以看了很多相关的paper。 在job talk 和 coding 结束当天，收到recruiter的信说要改变面试过程 把两轮的ai discussion 变成一轮coding 一轮system design, 虽然措手不及 也没办法， 面试结束后 等了三周 告知拒绝了，说反馈挺positive 但是coding的过程中虽然code quick， 但是没有主动考虑edge cases。 和recuiter说那就过6个月再试试。 没想到过了一周， 被recuiter介绍给他的同事，说问我愿不愿意考虑general ml position 我说可以啊， 电话里说因为之前的反馈很好，这次只要加面一轮coding，要注意写test， 于是准备了3周， 一月初 coding，两道题都是最优解 也主动说了test cases， recruiter说反馈很好 准备送交hiring committee 但是又要把我转给另一个recruiter。 最后这个recruiter 电话里完全不知道我之前的经历 让我给他讲一遍 还说那你之前coding 有问题啊 我说我刚刚follow-up coding，他才看到 说反馈是不错 但你这是 screening， 和真正的onsite不一样， 我解释完了他说准备送交debrief， 通过才送交hiring committee， 今天被告知没通过，理由是debrief认为我最后一次的coding 不代表onsite，recruiter 电话跟我解释 我才明白 是他们自己觉得可以加面一轮 就可以提交package 但是debrief的人根本不认可，说可以重新开始5轮on-site，想了想说三月初再说 recruiter说可以到时候和之前的职位一起重新面试， 唉 我真是服了fb的recuiter了 如果不确定就应该告诉我 这种拿人做实验的感觉真的很不好。
     * 第一次 面试 的题目是 以无奇灵， 就起三 ，和 物流凌
     *
     * 第二次 是 其儿斯 和义思儿罢
     * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=709290&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
     */

    /**
     * onsite遇到的全是地里原题，楼主差点没管理好表情笑出来，小时候考试都没这么爽过。但是也因为Uber做的太快被狂加sql，建议大家不要做太快，得演技实力并存想重点分享一下如何撸原题以及与面试官的互动经验，希望能帮助大家早日上岸。
     *
     * 如何撸原题
     * 1. 把地里所有帖子及回复认认真真粘贴下来，分别塞进三道题里
     * 2. 整理精华  删掉废话  打开脑洞  想象表格  练习coding与sql 还要多想几个方法.  楼主准备用window解那个time taken，结果面试官说不要用window， 始料未及，幸好兜住了
     * 3. product sense 其实也没那么难搞，我之前很怕这一块，但其实发现面试就前10min在bb这些东西。metrics和problem solving，谷歌搜一下真的都有。uber那个的话建议去看黑粉车的面筋，增加背景知识会让你滔滔不绝更自信。
     * 4. BQ.  楼主同时面了亚麻，14个LP都扛下来了，但是FB的bq也重点准备了。recruiter说这个很重要，因为是唯一的manager面，一定要自信与滔滔不绝。
     *
     *
     * 与面试官互动经验
     * 1. 当你看到原题时，要淡定，仍然要仔细读题，不要按自己脑洞里的题目走。先概括，再提问，然后滔滔不绝
     * 2. 写coding与sql的时候把思路念出来，这样面试官就可以知道你的思路，如果不对或者有bug他也可以及时纠正， 这样他们就会写下你communication与debug小能手的feedback
     * 3. 建议大家一定有技要炫，能用comprehensive list 就不要for loop， 能用any和zip就不要for loop，这样他们就会写下你coding小能手的feedback
     * 4. BQ面试官太严肃不要慌不要蔫，保持自信对他点头微笑并且滔滔不绝撑到最后。
     * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=711112&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
     */


    /**
     * 轮coding + 1轮bq + 1轮系统设计
     *
     * 1 coding，DOM traversal + array flatten
     * 2 system design，autocomplete 注重前端性能优化，api接口和前端工程化，无需service和db的implementation
     * 3 coding，event emitter + exclude map
     * 4 bq，有一个list 10+个问题
     *
     * 没有任何算法
     * coding全是js，看glassdoor有惊喜
     *
     */

    /**
     * Recruiter骚扰过几次，终于这次面了一下脸家
     * 电面在年前就过了，想着多几天准备就年后vo， 电面题忘了就不写了，记得是刷题网的interval变形
     * 后面就不加限制了，各位老板给点米吧，新人首发。
     * VO一共5轮
     * 亚裔小哥code了一道tax bucket的题，地理应该有出过，给一个tax bucket，{0-5000：0， 5001- 10000：0.2...}, 然后算要交多少tax， 然后又问了 刷题网旧屋山
     * 2轮，刷题网 儿散拔
     * system design面得烂，非常规题，设计一个html parser， 要跑在Botnet上，怪自己没听好题目，botnet关键词到喷了一半才想起来。。。
     * 俩behavior就不说了
     *
     */

    /**
     *
     BQ: conflict, positive/negative feedbacksCoding1: 以耳思舅，思易物
     Coding2: 酒其伞， 耳奇霸
     System: messenger
     ML: 推荐locations

     紧张等结果中，求祝福！
     */


    /**
     * 易寺灵 直接写了dfs没有用dp优化的 然后问followup口头walk through了dp优化 没有让写
     *
     * 易柳霸
     *
     * 二叉树后续迭代器
     *
     * 还有个忘了
     *
     * 设计题是botnet爬虫爬维基百科。老白面试官很有激情，一起讨论问题很舒服。讨论的一些要点有node fail之后具体怎么处理，load rebalance的细节，node恢复之后怎么加回来，还有replication之类的常规问题。
     *
     * 行为轮的面试官是个很有亲和力的烙印，问的问题也都比较常规。最后recuiter反馈说其他轮都是强hire但是这一轮是弱hire，我觉得还是expected的，自己的项目不是很亮眼，也不是太会编。fb面试总体体验很不错，面试官从交流中都能感觉到比较厉害，反应很敏捷，也都很make sense
     *
     * 求米啊！今天答题答错了被扣了一分我擦
     *
     */

    /**
     * 1. BQ，国人大姐（大不了多少），说有时间咱最后做个题，结果问了一堆问题，最后也没做题
     * 2. Design， 国人大哥（大不了多少），KV，答还行吧
     * 3. 算法，一群人的高度，问每个人能看到前面的人的个数。最后的最优解还有几行写完
     * 4. 3道刷题网的medium题
     * 5，一道热身，一个换currency的题，a->b (1:1.5), b->c (1:2)， 给俩钱，问他们的汇率, BFS
     */


}
