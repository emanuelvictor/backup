(function () {

    /**
     * Config
     */
    var moduleName = 'uploadImage';
    var templateUrl = './static/js/beta-components/upload-image/upload-image.html';

    /**
     * Module
     */
    var module;
    try {
        module = angular.module(moduleName);
    } catch (err) {
        module = angular.module(moduleName, []);
    }

    module.directive('uploadImage', ['$timeout', function ($timeout) {

        function Template(tElement, tAttributes) {

            tElement.data("mdIcon", tElement.find('md-icon'));
            return templateUrl;
        }

        function CompileHandler(tElement, tAttributes) {

            var mdIcon = tElement.data("mdIcon");

            tElement.find('div:eq(0)').prepend(mdIcon);

            return {
                pre: function preLink(scope, iElement, iAttrs) {
                },

                post: function postLink(scope, iElement, iAttrs, ctrl) {
                }
            };
        }

        function Controller($scope, $element, $attrs) {

            var uploadDiv = $element.find('.upload-image');

            var vm = this;

            vm.onSuccess = vm.onSuccess != undefined ? vm.onSuccess : function (input) {
            };

            vm.onDelete = vm.onDelete != undefined ? vm.onDelete : function (event) {
            };

            vm.onError = vm.onError != undefined ? vm.onError : function (msg) {
            };

            $scope.formats   = vm.formats;
            //$scope.source    = vm.source;
            $scope.maxSize   = parseInt($attrs.maxSize);
            $scope.minWidth  = parseInt($attrs.minWidth);
            $scope.minHeight = parseInt($attrs.minHeight);
            $scope.text      = $attrs.text;
            $scope.icon      = $attrs.icon;

            $scope.msgs = ['Error'];

            $scope.mouseOver = false;
            $scope.showThumb = false;

            $scope.file = '';
            $scope.source = '';

            uploadDiv.css('width', $attrs.minWidth);
            uploadDiv.css('height', $attrs.minHeight);
            uploadDiv.css('background-color', $attrs.color);

            var top = parseInt($attrs.minHeight) / 2;
            var middle = parseInt($attrs.minWidth) / 2;

            uploadDiv.find('p').css('height', $attrs.minHeight + 'px');
            uploadDiv.find('p').css('line-height', $attrs.minHeight + 'px');

            uploadDiv.find('md-icon:eq(0)').css('height', 'inherit');
            uploadDiv.find('md-icon:eq(0)').css('width', 'inherit');

            uploadDiv.find('div md-icon:eq(0)').css('line-height', (top - 10) + 'px');

            uploadDiv.find('div md-icon:eq(1)').css('height', (top - 30) + 'px');
            uploadDiv.find('div md-icon:eq(1)').css('line-height', (top - 30) + 'px');
            uploadDiv.find('div md-icon:eq(1)').css('left', (middle - 12) + 'px');

            if ($attrs.isCircle)
                uploadDiv.css('border-radius', '50%');

            var reader = new FileReader();

            $scope.$on(
                "$destroy",
                function handleDestroyEvent() {
                    uploadDiv.find('img').attr('src', '');
                    $scope.source = '';
                }
            );

            $scope.$watch('uploadImage.source', function (newValue, oldValue) {

                if(vm.source == '' || !vm.source)
                    newValue = null;

                if (newValue != oldValue && newValue != null) {

                    $scope.source = newValue;

                    uploadDiv.css('background-color', '#fff');
                    uploadDiv.find('md-icon:eq(0)').hide();

                    $scope.showThumb = true;
                } else {

                    $scope.showThumb = false;
                    uploadDiv.css('background-color', $attrs.color);
                    uploadDiv.find('md-icon:eq(0)').show();
                }
            }, true);

            $scope.isValidDimension = function(imageWidth, imageHeight) {
                if(imageWidth > $scope.minWidth || imageHeight > $scope.minHeight)
                    return true;
                vm.onError({msg: 'Selecione uma imagem com no mínimo ' + $scope.minWidth + 'x' + $scope.minHeight});
                $element.find('input').val('');
            };

            $scope.isValidSize = function (fileSize) {
                var maxSize = ($scope.maxSize * 1024) * 1024;
                if (maxSize > fileSize)
                    return true;
                vm.onError({msg: 'O tamanho do arquivo é maior do que o permitido. Tamanho máximo ' + $scope.maxSize + ' mb.'});
                $element.find('input').val('');
            };

            $scope.isValidFormat = function (fileFormat) {
                if ($scope.formats.indexOf(fileFormat) != -1)
                    return true;

                var formats = [];

                $scope.formats.forEach(function(format){
                  var format = format.split('/');
                  formats.push(format[1]);
                });

                vm.onError({msg: 'O tipo de arquivo selecionado é inválido. Somente ' + formats.slice(0, -1).join(', ')+' ou '+formats.slice(-1) + '.'});
                $element.find('input').val('');
            };

            uploadDiv.bind('click', function(event){

                var localName = event.target.localName;

                var className = event.target.className;

                if(localName !== 'input' && event.originalEvent && !className.match(/delete/)) {
                    $element.find('input').click();
                    event.preventDefault();
                } else if(className.match(/delete/)){

                    if($scope.source) {

                        vm.onDelete({event: event});
                    }
                }
            });

            reader.onloadend = function (evt) {
                if (reader.result) {

                    var image = new Image();

                    image.src = reader.result;

                    var height = image.height;
                    var width = image.width;

                    var img = document.createElement("img");
                    img.src = image.src;

                    height = img.height;
                    width = img.width;

                    if($scope.isValidDimension(width, height)) {

                        $scope.showThumb = true;

                        uploadDiv.css('background-color', '#fff');
                        uploadDiv.find('md-icon:eq(0)').hide();

                        $scope.source = reader.result;

                        $scope.file.base64 = reader.result;
                        $scope.file.element  = $element.find('input')[0];

                        vm.onSuccess({input: $scope.file});
                        $scope.$apply();
                    }
                }
            };

            $scope.onFileChange = function(element){
                $scope.$apply(function () {

                  if(element.files[0]) {
                    $scope.file = element.files[0];

                    if ($scope.isValidFormat($scope.file.type) && $scope.isValidSize($scope.file.size)) {

                      reader.readAsDataURL($scope.file);
                    }
                  }
                });
            };

            $element.bind('mouseover', function () {
                uploadDiv.find('div').css('display', 'block');
            });

            $element.bind('mouseout', function () {
                uploadDiv.find('div').css('display', 'none');
            });

        }

        return {
            restrict: 'E',
            priority: -1000,
            scope: true,
            bindToController: {
                formats: '=',
                maxSize: '=',
                source: '=',
                onDelete: '&',
                onSuccess: '&',
                onError: '&'
            },
            controllerAs: 'uploadImage',
            compile: CompileHandler,
            controller: Controller,
            templateUrl: Template
        }

    }]);
})();