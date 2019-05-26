import path from 'path'
import express from 'express'
import webpack from 'webpack'
import nodemailer from "nodemailer"

// 加载body-parser 处理post提交过来的数据
import bodyParser from 'body-parser';
var app = new express()
var apiRoutes = express.Router()

app.use('/', express.static('./dist'))

// bodyParser 设置,自动会在req上面添加
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
apiRoutes.post('/email', function(req, res) {
    debugger;
    console.info('req', req.body.name, req.body.email)
    var text = `这个人姓名叫${req.body.name }邮箱是${req.body.email}，欢迎骚扰`

    if (req.body.name && req.body.email) {
        res.json({
            iRet: 1,
            info: 'ok',
        });

        // Use Smtp Protocol to send Email
        var transporter = nodemailer.createTransport({
            //https://github.com/andris9/nodemailer-wellknown#supported-services 支持列表
            service: 'qq',
            port: 587, // SMTP 端口
            secure: false,
            // secureConnection: true, // 使用 SSL
            auth: {
                user:你的邮箱,
                //这里密码不是qq密码，是你设置的smtp密码
                pass: XXXXXXXX
            }
        });

        // setup e-mail data with unicode symbols
        var mailOptions = {
            to: req.body.email,
            from: 你的邮箱, // 这里的from和 上面的user 账号一样的
            subject: '我在学习发邮件', // 标题
            //text和html两者只支持一种
            text: text, // 标题
            // html: '<b>Hello world ?</b>' // html 内容
        };

        transporter.sendMail(mailOptions, function(error, info) {
            if (error) {
                return console.log(error);
            }
            console.log('邮件发送: ' + info.response);
            transporter.close();
        });

    } else {
        res.json({
            iRet: -1,
            info: 'error',
        });
        return;
    }
});
app.use('/api', apiRoutes)


app.listen(8081, function() {
    console.info('复制打开浏览器', 'localhost:8081')
})