var app = getApp()

Page({
  data: {

  },
  allowlogin: function () {
    app.getUserInfo(function (param) {
      wx.navigateBack({
        delta: 1,
      })
    });
  },
  rejectlogin: function () {
    wx.showModal({
      title: '提示',
      content: '取消登录将导致部分功能不可用',
      success: function (res) {
        if (res.confirm) {
          wx.navigateBack({
            delta: 1, // 回退前 delta(默认为1) 页面
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
        }
      }
    })
  }
})