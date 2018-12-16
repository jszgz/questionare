var app=getApp()

Page({
    data: {
        quesorder: 0,
        array: [],
        optioncontent: null,
        nextquesid: null,
        optionsForQuesid: [],
        questypeid: 0,
        questypearray: ['单选题', '多选题', '问答题'],
        current: "survey",
        surveyname: null,
        surveydescription: null,
        surveyperoration: null,
        surveyendtime: "未知",
        queses: [],
        user:null,
    },
    bindDateChange: function (e) {
        this.setData({
            surveyendtime: e.detail.value+' 16:00:00'
        })
    },
    surveycomplete: function (e) {
        this.setData({
            current: "ques",
            surveyname: e.detail.value.surveyname,
            surveydescription: e.detail.value.surveydescription,
            surveyperoration: e.detail.value.surveyperoration,
        })
    },
    tosurvey: function () {
        this.setData({
            current: "survey",
        })
    },
    toques: function () {
        this.setData({
            current: "ques",
        })
    },
    newques: function () {
        this.setData({
            current: "option",
        })
    },
    bindTypeChange: function (e) {
        this.setData({
            questypeid: parseInt(e.detail.value)
        })
    },
    addoption: function () {
        var arrayy = this.data.array.concat(this.data.array.length + 1)
        this.setData({
            array: arrayy
        })
    },
    quescomplete: function (e) {
        this.setData({
            quesorder: this.data.quesorder + 1,
        })
        for (var i = 0; i < this.data.array.length; i++) {
            var optionkey = "option" + i;
            var optionnextkey = "optionnext" + i;
            var nextquesidd = e.detail.value[optionnextkey]
            if (nextquesidd == '')
                nextquesidd = this.data.quesorder + 1;

            var optionsForQuesidd = this.data.optionsForQuesid.concat([{ optioncontent: e.detail.value[optionkey], nextquesid: nextquesidd }])
            this.setData({
                optionsForQuesid: optionsForQuesidd
            })
        }
        //填空题
        if(this.data.questypeid==2)
        {
                        var optionsForQuesidd = [{ optioncontent: "", nextquesid: this.data.quesorder + 1 }]
            this.setData({
                optionsForQuesid: optionsForQuesidd
            })
        }

        var quesess = this.data.queses.concat([{ quescontent: e.detail.value.quescontent, questypeid: 1+parseInt(this.data.questypeid), quesorder: this.data.quesorder, quesrequired: e.detail.value.quesrequired, optionsForQuesid: this.data.optionsForQuesid }])
        this.setData({
            queses: quesess,
            questypeid:0,
        })
        this.setData({
            current: "ques",
        })
        this.setData({
            array: [],
            optionsForQuesid: [],
        })
    },
    completeall: function () {

        this.setData({
            user: app.globalData.userInfo,
        })

        wx.showToast({
            title: '提交中',
            icon: 'loading',
            duration: 10000,
            mask: true
        })
        wx.request({
            url: 'https://chwangteng.cn/Paper/submitnewsurvey.action',
            data: {
                survey:this.data,
                queses:this.data.queses,
                user:app.globalData.userInfo,
            },
            method: 'POST',
            success: function (res) {
                wx.hideToast()
                wx.showToast({
            title: '提交成功',
            icon: 'success',
            mask: true
        })
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