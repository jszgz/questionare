var app = getApp()

Page({
  data: {
    preface_url: 'https://chwangteng.cn/Paper/images/paper_preface.png',
    currentpage: 0,
    progresspercent: 0,
    survey: null,
    answers: [],
  },
  nextpage: function () {
    var that = this
    wx.getStorage({
      key: 'notfirstlogin',
      success: function (res) {
        // success
        if (app.globalData.userInfo == null) {
          app.getUserInfo()
          that.setData({
            currentpage: that.data.currentpage + 1
          })

        }
        else {
          that.setData({
            currentpage: that.data.currentpage + 1
          })
        }
      },
      fail: function () {
        // fail
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
      complete: function () {
        // complete
      }
    })



  },
  prepage: function () {
    this.setData({
      currentpage: this.data.currentpage - 1
    })
  },
  singleChoice: function () {
    this.setData({
      progresspercent: this.data.currentpage / this.data.survey.queses.length,
      currentpage: this.data.currentpage + 1,
    })
  },
  complete: function (e) {
    console.log(e)
    var myanswers = []
    for (var i = 0; i < this.data.survey.queses.length; i++) {
      var key = "ques" + this.data.survey.queses[i].quesorder;

      if (this.data.survey.queses[i].questype.typeid == 3) {
        var answerkey = "answer" + this.data.survey.queses[i].quesorder;
        myanswers = myanswers.concat({ optionid: parseInt(e.detail.value[key]), answercontent: e.detail.value[answerkey] })
      } else {
        myanswers = myanswers.concat({ optionid: parseInt(e.detail.value[key]), answercontent: "" })
      }

    }
    this.setData({
      answers: myanswers
    })

    var that = this

    wx.showToast({
      title: '提交中',
      icon: 'loading',
      duration: 10000
    })

    wx.request({
      url: 'https://chwangteng.cn/Paper/completeSurvey.action',
      data: {
        userid: app.globalData.userInfo.userid,
        optionList: this.data.answers,
      },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      // header: {}, // 设置请求的 header
      success: function (res) {
        // success

        that.setData({
          currentpage: parseInt(that.data.currentpage + 1),
        })
        wx.hideToast()

        wx.setNavigationBarTitle({
          title: '完成',
          success: function (res) {
            // success
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
  navback: function () {
    wx.navigateBack({
      delta: 1, // 回退前 delta(默认为1) 页面
    })
  },
  onLoad: function (param) {
    wx.showNavigationBarLoading()
    var that = this;
    wx.request({
      url: 'https://chwangteng.cn/Paper/showSurvey.action',
      data: {
        surveyid: param.id,
      },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      // header: {}, // 设置请求的 header
      success: function (res) {
        // success
        that.data.survey = res.data.surveyresponse
        that.setData({
          survey: res.data.surveyresponse
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
  }
})