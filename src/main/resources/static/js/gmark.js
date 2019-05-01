$(function() {
    layui.use('form', function(){
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

        form.on('submit(gmarkSubmit)', function(data){

           var website = $("#website option:selected").val();
           var blogUrl = $("#blogUrl").val();
            var pictureFilePath = $("#pictureFilePath").val();
            var imageUrl = $("#imageUrl").val();

           var  params ={"website":website,
                         "blogUrl":blogUrl,
                         "pictureFilePath": pictureFilePath,
                         "imageUrl":imageUrl};
           $.ajax({
                contentType: "application/json; charset=utf-8",
                url: data.form.action,
                type: data.form.method,
                dataType:'json',
                data: JSON.stringify(params),
                success: function(responseData) {
                    alert(responseData);
                }
           });

           return false;
        });
    });

});

