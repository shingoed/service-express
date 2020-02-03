

$(document).ready(function() {

    $('.nBtn, .table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if(text=='Edit'){

            $.get(href,function(product,status){
                $('.myForm #id').val(product.id)
                $('.myForm #itemCode').val(product.itemCode)
                $('.myForm #name').val(product.name)
                $('.myForm #width').val(product.width)
                $('.myForm #length').val(product.length)
                $('.myForm #color').val(product.color)
                $('.myForm #style').val(product.style)
                $('.myForm #type').val(product.type)
            });

            $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #itemCode').val('')
            $('.myForm #name').val('')
            $('.myForm #width').val('')
            $('.myForm #length').val('')
            $('.myForm #color').val('')
            $('.myForm #style').val('')
            $('.myForm #type').val('')
            $('.myForm #exampleModal').modal();

        }
    });

    $('.table .delBtn').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delReference').attr('href', href);
        $('#deleteModal').modal();



    });
});