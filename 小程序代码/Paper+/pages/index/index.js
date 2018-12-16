//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: 'Hello World',
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {

  },
  onShareAppMessage: function () {
    var title=app.globalData.userInfo.nickName+'邀请您体验岂纸问卷';
    return {
      title: title,
      path: '/pages/index/index'
    }
  }
})
