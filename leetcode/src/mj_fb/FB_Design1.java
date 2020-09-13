package mj_fb;

public class FB_Design1 {

    /**
     * Design memcache. 答LRU应该可以, 但问了很多bottleneck和threadsafe
     */

    /**
     * 设计一个单机的KV缓存
     */

    /**
     * Design: Tiny URL
     */

    /**
     * Design twitter feed system.
     */

    /**
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218824&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	 *
	 * 第四轮system design，美国小哥，问如何设计高性能的VR系统。。。
	 * 完全出乎意料。。。答得乱七八糟。。。顺便请教地里的大神们该如何回答。。。从这轮开始状态直线下降。。。
	 *
	 * 第六轮system design，美国老哥。。。设计不阻塞主线程的多线程系统。。。本身没太多经验，把我知道的都说了，看他怎么给分了
	 *
	 *
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218800&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	 * system design，如果是硕士或者是跳槽一年以下的似乎是不用system design的，我抽到了shorten long URL
	 *
	 * 设计一个chat app，主要讨论api和本地缓存数据库怎么设计
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=213983&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	 *
	 *
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=193545&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	 * 一道多个pc之间发送和请求数据的设计题
	 * 是不是FB爱问的它自家的Cassandra怎样通过gossip算法同步各个db之间的数据，收敛速度等相关知识
	 *
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218491&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	 * design是设计post和friend的搜索 支持多个关键词
	 *
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=215388&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	 * 最后一轮写代码要应对multi-thread的情况，还要throw excetion。
	 *
	 *
	 *
	 * design http get/post.
	 *
	 *
	 * 白， 设计privacy settings
	 * 6. 印， 设计interested points
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218190&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	 *
	 * privacy settings 要求实现怎么判断某些东西对某些人可见或者不可见，怎么分组，怎么设计api，code大概怎么写，使用哪些数据，数据怎么存
	 *
	 * interested points 提供5Million points (longitude+latitude)，输入一个地址，找出一定范围内的interested points，怎么处理request，怎么处理大数据返回，怎么存数据
	 *
	 *
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=208941&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	 *
	 * 设计一个FB的搜索系统， 自我感觉讨论不错， 然并卵 从requriment开始： 要搜索什么？ people,post, event ....
	 * constraint ， 用户多少，数据多少之类；
	 * UI 怎么搞， 怎样提高用户体验， typehead, 不同label分类
	 * 总体怎么设计， 前段， server， 数据，画一画
	 * workflow 怎样，写个流程， 一个请求怎么完成
	 * webservice怎么设计？ API， operation是怎么定义， 把restful讲讲
	 * 数据库， 搜索的数据结构都怎么存，SQL table啦，还有 trie啦， bloom filter 啦， inverted table都讲讲。
	 * CAP那一套说一说，怎么balance， 怎么Partion， 怎么保证consistence, cache怎么存
	 *
	 *
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=148865&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3088%255D%255Bvalue%255D%3D1%26searchoption%255B3088%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3090%255D%255Bvalue%255D%3D1%26searchoption%255B3090%255D%255Btype%255D%3Dradio%26searchoption%255B3046%255D%255Bvalue%255D%3D2%26searchoption%255B3046%255D%255Btype%255D%3Dradio&page=1
	 *  Pirate. Design Wikipedia crawler.
                  followup 1: No global status.
                  followup 2: deal with machine failure
                  followup 3: make the wiki site unaware of this crawler.

	 * 1. distributed bfs
	 * 2. consistent hashing
	 * 3. assign task with a random delay
	 */

    /**
     * N Queens, 和leetcode上的题差不多，只不过只要求输出true or false
     *
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=208301&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
     * 在fb上发布新状态并能即时搜索其他人发布的新状态；为已知的附近餐馆排序显示在推荐列表中
     *
     *
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216431&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
     *
     * sys design， friend search。完全没有问scale，就一直纠结在怎么存人上，
     * tree 也不行， hash map也不好， list也不好。。。没有search engine经验的真是不知道他想要啥。。
     *
     *
     */

    /**
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=215017&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
     * 设计newsfeed api
     *
     * 加面一轮design。题目是网页上的很多字符内容需要根据用户所处的地区来用当地语言显示，怎么实现这个功能
     *
     *
     */

    /**
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216560&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
     * design message returned from different language，instagram的一个欧洲manager，问design，
     * 说app里面不同语言的功能怎么设计，clarify一下之后是说，比如你有个app提醒是“你有x条新信息“，
     * "You have x new messages"...这样，支持多种语言，写了个简单的API，我说多加一层的翻译，
     * 用个两层的hash table，第一层的key是语言，第二层是信息的类型，value就是"You have x new messages"，
     * 他又问那之间的那个x怎么处理，我说就类似%d这样，最后再替换。他又问怎么存这个hash table，我说用cassandra，
     * 其实我也不懂cassandra，就知道有sharding key和column key排序，感觉好像很合适的样子，
     * 他犹豫了一下问我为什么cassandra比较好，我就说我也不知道，都可以吧，memcached也可以，
     * 因为我确实不懂这方面，就听过一点点，不敢瞎说，他又问如果这个message里面有多个数字怎么办，
     * 我就说那可以把message拆成几段再拼起来，数字就不翻译了，后来他又问怎么让programmer更容易的加新的类型的信息，
     * 我想了一会儿表示不会，希望有大神能给解释一下，最后他还很nice的表示希望可以看到你来fb
     */
}
