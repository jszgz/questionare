//app.js
App({
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },
  getUserInfo: function (cb) {
    var that = this
        wx.login({
          success: function (res) {
            console.log(res)
            console.log("try to get userino")
            wx.getUserInfo({
              success: function (resinfo) {
                //
                console.log(resinfo)
                wx.showNavigationBarLoading()
                wx.request({
                  url: 'https://chwangteng.cn/Paper/login.action',
                  data: {
                    code: res.code,
                    userInfo: resinfo.userInfo
                  },
                  method: 'POST',
                  success: function (response) {
                    // success
                    console.log(" see what does it return")
                    console.log(response)
                    that.globalData.userInfo = response.data.userInfo
                    typeof cb == "function" && cb(that.globalData.userInfo)
                    wx.hideNavigationBarLoading()
                     
                    wx.setStorage({
                      key: 'notfirstlogin',
                      data: true,
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
                  fail: function () {
                    // fail
                  },
                  complete: function () {
                    // complete
                  }
                })


              },
              fail: function (res) {
                wx.showModal({
                  title: '未获取到公开信息',
                  content: '因微信限制，取消获取信息后短时间内无法再次获取信息，请删除小程序后再次添加或稍后再试',
                  success: function (res) {
                    if (res.confirm) {
                      wx.navigateBack({
                        delta: 1,
                      })
                    }
                  }
                })
              },
              complete: function () {
                // complete
              }
            })
          }
        })
  },

  

  globalData: {
    userInfo: null
  }
})