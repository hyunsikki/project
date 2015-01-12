/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

// image.js의 34번째줄 (c.config.image_previewText||"") 이미지 미리보기 문구

CKEDITOR.editorConfig = function( config ) {
	
	config.resize_enabled = false;
	config.height = '350';

	config.removePlugins = 'resize';
	config.extraPlugins = 'tableresize';
	config.extraPlugins = 'imageresize';
	
	config.toolbar = [
      	{ name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', 'Undo', 'Redo' ] },
      	{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ], items: [ 'NumberedList', 'BulletedList', 'Outdent', 'Indent', 'Blockquote', 'CreateDiv', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', 'BidiLtr', 'BidiRtl' ] },
      	{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', 'RemoveFormat' ] },
      	'/',
      	{ name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
      	{ name: 'insert', items: [ 'Image', 'Table', 'HorizontalRule', 'SpecialChar', 'PageBreak'] },
      	{ name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] },
      	{ name: 'colors', items: [ 'TextColor', 'BGColor' ] }
	];
};
