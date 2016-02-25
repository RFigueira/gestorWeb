if (!com)
    var com = {}
if (!com.corejsf) {
    var focusLostTimeout
    com.corejsf = {
        errorHandler: function (data) {

            alert("Erro na requisição ajax" + data.description)
        },
        updeteCompletionItems: function (input, event) {

            var keyStoreTimeout
            jsf.ajax.addOnError(com.corejsf.errorHandler)

            var ajaxRequest = function () {

                jsf.ajax.request(input, event, {
                    render: com.corejsf.getListboxId(input),
                    x: Element.cumulativeOffset(input)[0],
                    y: Element.cumulativeOffset(input)[1] + Element.getHeigth(input)

                })


            }

            window.clearTimeout(keyStoreTimeout)
            keyStoreTimeout = window.setTimeout(ajaxRequest, 350)


        },
        inputLostFocus: function (input) {
            var hideListBox = function () {
                Element.hide(com.corejsf.getListboxId(input))


            }

            focusLostTimeout = window.setTimeout(hideListBox, 200)


        },
        
        getListBox: function (input) {
            
            var clienteId = new String(input.name)
            var lastIndex = clienteId.lastIndexOf(":")
            return clienteId.substring(0, lastIndex) + ":listbox"
            
            
        }
    }

}


