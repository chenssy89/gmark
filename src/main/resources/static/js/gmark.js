$(function() {
    layui.use('form', function(){
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

        form.on('submit(gmarkSubmit)', function(data){

           var website = $("#website option:selected").val();
           var blogUrl = $("#blogUrl").val();
           var imagePath = $("#imagePath").val();
           var imageUrl = $("#imageUrl").val();
           var imageName = $("#imageName").val();


           var  params ={"website":website,
                         "blogUrl":blogUrl,
                         "imagePath": imagePath,
                         "imageUrl":imageUrl,
                         "imageName":imageName};

           $("#markdownValue").val("内容采集中...");
           $.ajax({
                contentType: "application/json; charset=utf-8",
                url: data.form.action,
                type: data.form.method,
                dataType:'json',
                data: JSON.stringify(params),
                success: function(responseData) {
                    $("#markdownValue").val(responseData.markdown);
                }
           });

           return false;
        });
    });

});

