  function updateCoords(c)
  {
    $('#x').val(c.x);
    $('#y').val(c.y);
    $('#w').val(c.w);
    $('#h').val(c.h);
  };

  function checkCoords()
  {
    if (parseInt($('#w').val())) return true;
    alert('Please select a crop region then press submit.');
    return false;
  };



$(document).ready(function(){

    // Create variables (in this scope) to hold the API and image size
    var jcrop_api=null,
        boundx,
        boundy,

        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        xsize = $pcnt.width(),
        ysize = $pcnt.height();
    
    $("#inputimg").change(function (){
      var imgsrc = $("#inputimg")[0].files[0];
      var src = window.URL.createObjectURL(imgsrc);
      $("#cropbox").attr("src", src);
      $("#previewimg").attr("src",src);
      if (jcrop_api != null){
          jcrop_api.setImage(src);
          jcrop_api.setOptions({
              onChange: updatePreview,
              onSelect: updatePreview,
              aspectRatio: 1
            },
            function(){
              // Use the API to get the real image size
              // Store the API in the jcrop_api variable
              $preview = $('#preview-pane');
              jcrop_api = this;
              // Move the preview into the jcrop container for css positioning
              $preview.appendTo(jcrop_api.ui.holder);
          });
      }
      else{
        $('#cropbox').Jcrop({
              onChange: updatePreview,
              onSelect: updatePreview,
              aspectRatio: 1
            },function(){
          // Use the API to get the real image size
          var bounds = this.getBounds();
          boundx = bounds[0];
          boundy = bounds[1];
          // Store the API in the jcrop_api variable
          jcrop_api = this;

          // Move the preview into the jcrop container for css positioning
          $preview.appendTo(jcrop_api.ui.holder);
        });
      }
    });

    console.log('init',[xsize,ysize]);
    // $('#cropbox').Jcrop({
    //       onChange: updatePreview,
    //       onSelect: updatePreview,
    //       aspectRatio: 1
    //     },function(){
    //   // Use the API to get the real image size
    //   var bounds = this.getBounds();
    //   boundx = bounds[0];
    //   boundy = bounds[1];
    //   // Store the API in the jcrop_api variable
    //   jcrop_api = this;

    //   // Move the preview into the jcrop container for css positioning
    //   $preview.appendTo(jcrop_api.ui.holder);
    // });

    function updatePreview(c)
    {
      if (parseInt(c.w) > 0)
      {
        $('#x').val(c.x);
        $('#y').val(c.y);
        $('#w').val(c.w);
        $('#h').val(c.h);
        var rx = xsize / c.w;
        var ry = ysize / c.h;
        var bounds = jcrop_api.getBounds();
        boundx = bounds[0];
        boundy = bounds[1];
        
        $pimg.css({
          width: Math.round(rx * boundx) + 'px',
          height: Math.round(ry * boundy) + 'px',
          marginLeft: '-' + Math.round(rx * c.x) + 'px',
          marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
      }
    };

});