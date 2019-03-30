<template>
    <Row :gutter="8">
        <Col span="4" v-for="iconData in iconDatas" :key="iconData.title">
            <div><iconCard :iconData='iconData'/></div>
        </Col>
    </Row>
</template>
<script>
import iconCard from './iconCard'
export default {
    name:'iconList',
    data(){
        return{
            iconDatas:[
                {title:"百度",url:"https://www.baidu.com",image:require("../images/icon_baidu_logo.png")},
                {title:"谷歌",url:"https://www.google.com.hk/",image:require("../images/icon_google_logo.png")},
                {title:"掘金",url:"https://juejin.im/",image:require("../images/icon_juejin_logo.png")},
                {title:"知乎",url:"https://www.zhihu.com/",image:require("../images/icon_zhihu_logo.png")},
                {title:"简书",url:"https://www.jianshu.com/",image:require("../images/ico_jianshu_logo.png")},
                {title:"",url:"add",image:require("../images/icon_add_logo.png")}
            ]
        }
    },
    components:{
        iconCard
    }
}
</script>
