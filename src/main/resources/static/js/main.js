

$(document).ready(function() {

    $('.table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var hrefID = href.substr(href.lastIndexOf('/') + 1);
        console.log("THIS IS HREF"+href);
        console.log("THIS IS HREFID"+hrefID);

        // find all the # after the last /
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
        }else if(text=='Edit Quantity'){
        // only show the form with the correct #
            $('.myForm'+hrefID+' #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        var hrefID = href.substr(href.lastIndexOf('/') + 1);
        $('#deleteFormClient').attr('action', '/mycart/delete/'+ hrefID );
        $('#deleteModal #delReference').attr('href', href);
        $('#deleteModal').modal();



    });
});