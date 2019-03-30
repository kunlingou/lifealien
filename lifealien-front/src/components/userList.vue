<template>
    <div class='userList'>
        <!-- <h2>用户列表</h2> -->
        <Table :columns="user_columns" :data="users" @click="postTest"></Table>
    </div>
</template>
<script>
var _self;
export default {
    name:'userList',
    data(){
        return{
            users:[],
            user_columns:[
                {title:'编号', key:'id'},
                {title:'姓名', key:'name'},
                {title:'用户名', key:'userName'},
                {title:'密码', key:'password'},
            ]
        }
    },
    created(){
        _self = this;
        this.postTest()
    },
    methods:{
        postTest(){
            _self.$http.get(_self.$rootUrl+'/api/persons',{
                params:{
                    page:1,
                    sex:'',
                    email:''
                }
            }).then(function(res){
                console.log(res);
                _self.users = res.data.data.results
            }).catch(function(err){
                console.log(err)
            });
        }
    }
}
</script>

