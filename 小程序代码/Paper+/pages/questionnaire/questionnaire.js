Page({
    data: {
        imgUrls: [
            {
                id: 7,
                src: 'https://chwangteng.cn/Paper/images/swiper1.jpg'
            },
            {
                id: 8,
                src: 'https://chwangteng.cn/Paper/images/swiper2.jpg'
            },
            {
                id: 9,
                src: 'https://chwangteng.cn/Paper/images/swiper3.jpg'
            },
        ],
        surveys: [],
    },
    onLoad: function () {
        this.refreshmethod(true)
    },
    onPullDownRefresh: function () {
        this.refreshmethod(false)
    },
    refreshmethod: function (load) {
        wx.showNavigationBarLoading()
        var that = this
        wx.request({
            url: 'https://chwangteng.cn/Paper/showAllUserSurvey.action',
            data: {
            },
            method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
            // header: {}, // 设置请求的 header
            success: function (res) {
                // success
                that.data.surveys = res.data.surveyList;
                that.setData({
                    surveys: res.data.surveyList
                })
                wx.hideNavigationBarLoading()
                if (load == false)
                    wx.stopPullDownRefresh()
            },
            fail: function () {
                // fail
            },
            complete: function () {
                // complete
            }
        })
    }
})