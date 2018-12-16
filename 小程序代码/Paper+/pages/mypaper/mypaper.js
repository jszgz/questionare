var app = getApp()

Page({
  data:
  {
    userInfo: null,
    surveys: null,
  },
  showdetail:function(e){
    console.log(e.currentTarget.id)
    var urlll='../statistics/statistics?id='+e.currentTarget.id
    wx.navigateTo({
      url: urlll,
      success: function(res){
        // success
      },
      fail: function() {
        // fail
      },
      complete: function() {
        // complete
      }
    })
  },
  onLoad: function () {
    wx.showNavigationBarLoading()
    var that = this
    console.log("app.globalData.userInfo:" + app.globalData.userInfo)
    this.setData({
      userInfo: app.globalData.userInfo
    })
    wx.request({
      url: 'https://chwangteng.cn/Paper/showAllSurvey.action',
      data: {
        userInfo: this.data.userInfo
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
      },
      fail: function () {
        // fail
      },
      complete: function () {
        // complete
      }
    })
  },

})