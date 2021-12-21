package com.bw.library_common.router.router;

public
class ARouterFragmentPath {

    /** 首页组件 */
    public static class Home
    {
        private static final String HOME = "/home";

        /** 首页 */
        public static final String PAGER_HOME = HOME + "/Home";

    }

    /** 分类组件 */
    public static class Classify
    {
        private static final String CLASSIFY = "/classify";

        /** 分类 */
        public static final String PAGER_CLASSIFY = CLASSIFY + "/Classify";

    }

    /** 购物车组件 */
    public static class Shopping
    {
        private static final String SHOP = "/shop";

        /** 购物车 */
        public static final String PAGER_SHOP = SHOP + "/Shop";

    }

    /** 消息组件 */
    public static class Message
    {
        private static final String MESSAGE = "/message";

        /** 消息 */
        public static final String PAGER_MESSAGE = MESSAGE + "/Message";

    }

    /** 我的组件 */
    public static class Mine
    {
        private static final String MINE = "/mine";

        /** 我的 */
        public static final String PAGER_MINE = MINE + "/Mine";

    }

}
