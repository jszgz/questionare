var app=getApp()

Page({
    data: {
        id: null,
        survey: null,
        color: [
            "#ffbe26",
            "#fc5b23",
            "#198ded",
            "#3bc06b",
            "#c90ff0"
        ],
        style: "wu",
    },
    onShareAppMessage: function () {
        var url = '/pages/show_questionnaire/show_questionnaire?id=' + this.data.id;
        console.log(url);

        var title=app.globalData.userInfo.nickName+"邀您填写"+"《"+this.data.survey.surveyname+"》"

        return {
            title: title,
            path: url,
        }
    },
    onLoad: function (e) {
        this.setData({
            id: e.id
        })
        wx.showNavigationBarLoading()
        var that = this;
        wx.request({
            url: 'https://chwangteng.cn/Paper/statistics.action',
            data: {
                surveyid: parseInt(this.data.id),
            },
            method: 'POST',
            success: function (res) {
                // success
                that.data.survey = res.data.surveyresponse
                that.setData({
                    survey: res.data.surveyresponse
                })
                wx.hideNavigationBarLoading()
                wx.setNavigationBarTitle({
                  title: that.data.survey.surveyname,
                  success: function(res) {
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
    changestyle: function () {
        var that = this;
        wx.showActionSheet({
            itemList: ['无图表', '条形图', '堆积图'],
            success: function (res) {
                console.log(res.tapIndex)
                if (res.tapIndex == 0) {
                    that.setData({
                        style: "wu"
                    })
                } else if (res.tapIndex == 1) {
                    that.setData({
                        style: "tiaoxing"
                    })
                    that.drawtiaoxing()
                } else if (res.tapIndex == 2) {
                    that.setData({
                        style: "duiji"
                    })
                    that.drawduiji()
                }
            },
            fail: function (res) {
                console.log(res.errMsg)
            }
        })
    },
    drawduiji: function () {
        console.log("draw duiji")
        var that = this
        var res = wx.getSystemInfoSync()
        for (var i = 0; i < that.data.survey.queses.length; i++) {
            var question = that.data.survey.queses[i]
            var context = wx.createContext()
            var totalnumber = 0
            //获取所有人数充满屏幕宽度，以使每个选项有对应的宽度
            for (var j = 0; j < question.optionsForQuesid.length; j++) {
                    totalnumber = totalnumber+question.optionsForQuesid[j].answers.length
            }

            var current=0
            var horizontaloffset=0;
            //开始绘制每个选项的图形条
            for (var k = 0; k < question.optionsForQuesid.length; k++) {
                var option = question.optionsForQuesid[k]
                
                horizontaloffset=current/totalnumber* res.windowWidth

                current=current+option.answers.length

                var width = option.answers.length / totalnumber * res.windowWidth
                //防止人数为0的时候没有长度显示
                if (width == 0)
                    width = 2;
                
                context.beginPath()
                context.setFillStyle(that.data.color[k % that.data.color.length])
                context.rect(horizontaloffset, 0, width, res.windowWidth * 100 / 750)
                context.fill()
            }
            wx.drawCanvas({
                canvasId: 'Canvasduiji' + parseInt(i + 1),
                actions: context.getActions()
            })

        }
    },

    drawtiaoxing: function () {
        var that = this
        var res = wx.getSystemInfoSync()
        for (var i = 0; i < that.data.survey.queses.length; i++) {

            var question = that.data.survey.queses[i]
            var context = wx.createContext()
            var maxnumber = 0
            //获取最多人数的选项使图形充满屏幕宽度，其他选项根据比例调整长度
            for (var j = 0; j < question.optionsForQuesid.length; j++) {
                if (question.optionsForQuesid[j].answers.length > maxnumber) {
                    maxnumber = question.optionsForQuesid[j].answers.length
                }
            }

            //开始绘制每个选项的图形条
            for (var k = 0; k < question.optionsForQuesid.length; k++) {
                var option = question.optionsForQuesid[k]
                var recheight = res.windowWidth * 200 / 750 / question.optionsForQuesid.length
                var vertioffset = k * recheight
                var width = option.answers.length / maxnumber * res.windowWidth
                //防止人数为0的时候没有长度显示
                if (width == 0)
                    width = 2;

                context.beginPath()
                context.setFillStyle(that.data.color[k % that.data.color.length])
                context.rect(0, vertioffset, width, recheight)
                context.fill()
            }
            wx.drawCanvas({
                canvasId: 'Canvastiaoxing' + parseInt(i + 1),
                actions: context.getActions()
            })

        }
    }
})