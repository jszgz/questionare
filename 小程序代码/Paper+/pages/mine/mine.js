var app = getApp()

Page({
  data:
  {
    userInfo: null
  },

  tologin: function () {
    wx.navigateTo({
      url: '../login/login',
      success: function (res) {
        // success
      },
      fail: function () {
        // fail
      },
      complete: function () {
        // complete
      }
    })
  },
  onShow: function () {
    console.log("app.globalData.userInfo:" + app.globalData.userInfo)
    var that = this
    that.setData({
      userInfo: app.globalData.userInfo
    })
    wx.getStorage({
      key: 'notfirstlogin',
      success: function (res) {
        //调用应用实例的方法获取全局数据
        app.getUserInfo(function (userInfo) {
          //更新数据
          that.setData({
            userInfo: userInfo
          })
        })
      },
      fail: function () {

      },
      complete: function () {
        // complete
      }
    })
  },
  newquestionnaire: function () {
    console.log("newnewnew")
    wx.navigateTo({
      url: '../newquestionnaire/newquestionnaire',
    })
  },
  myquestionnaire: function () {
    wx.navigateTo({
      url: '../mypaper/mypaper',
    })
  }

})