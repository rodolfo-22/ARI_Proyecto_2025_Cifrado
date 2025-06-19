import { useState } from "react";
import { ToastContainer, toast, Slide } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { convertFile } from "./services/convertService";
import type { ConvertEndpoint } from "./services/convertService";

function App() {
  const [fileContent, setFileContent] = useState<string>("");
  const [generatedContent, setGeneratedContent] = useState<string>("");
  const [delimitador, setDelimitador] = useState<string>(",");
  const [clave, setClave] = useState<string>("");
  const [file, setFile] = useState<File | null>(null);
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleFileUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0];
    if (selectedFile) {
      setFile(selectedFile);
      const reader = new FileReader();
      reader.onload = (e) => setFileContent(e.target?.result as string);
      reader.readAsText(selectedFile);
    }
  };

  const descargarArchivo = (blob: Blob, nombre: string) => {
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = nombre;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    URL.revokeObjectURL(url);

    blob.text().then(setGeneratedContent);
  };

  const handleConversion = async (tipo: ConvertEndpoint, nombreArchivo: string) => {
    if (!file) return setErrorMsg("Selecciona un archivo primero.");
    try {
      const blob = await convertFile(tipo, file, delimitador, clave);
      descargarArchivo(blob, nombreArchivo);
    } catch (error: any) {
      console.error(error);
      toast.error(error.message || `Error al convertir archivo (${tipo})`);

    }
  };

  const handleConvertToTxt = async () => {
    if (!file) return setErrorMsg("Selecciona un archivo primero.");
    const ext = file.name.split(".").pop()?.toLowerCase();
    if (ext === "json") {
      await handleConversion("json-to-txt", "archivo_convertido.txt");
    } else if (ext === "xml") {
      await handleConversion("xml-to-txt", "archivo_convertido.txt");
    } else {
      toast.error("Solo se puede convertir a TXT desde un archivo JSON o XML.");

    }
  };

  return (
    <><div className="min-h-screen p-6 bg-gradient-to-br from-gray-100 to-gray-200">
      <div className="max-w-7xl mx-auto bg-white p-8 rounded-3xl shadow-2xl space-y-6">
        <h1 className="text-3xl font-bold text-center text-gray-800">Transformar Archivos</h1>

        <div className="flex flex-col gap-4">
          <label className="font-semibold">Seleccionar archivo de origen:</label>
          <input type="file" accept=".txt,.json,.xml" onChange={handleFileUpload} className="p-2 border rounded-lg" />
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <label className="block font-semibold mb-1">Contenido original:</label>
            <textarea className="w-full h-60 p-3 border rounded-lg" value={fileContent} readOnly />
          </div>
          <div>
            <label className="block font-semibold mb-1">Contenido generado:</label>
            <textarea className="w-full h-60 p-3 border rounded-lg" value={generatedContent} readOnly />
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <label className="block font-semibold mb-1">Delimitador:</label>
            <input
              type="text"
              className="w-full p-3 border rounded-lg"
              value={delimitador}
              onChange={(e) => setDelimitador(e.target.value)} />
          </div>
          <div>
            <label className="block font-semibold mb-1">Clave de cifrado:</label>
            <input
              type="password"
              className="w-full p-3 border rounded-lg"
              value={clave}
              onChange={(e) => setClave(e.target.value)} />
          </div>
        </div>

        <div className="flex flex-col md:flex-row justify-center gap-4 pt-6">
          <button
            className="bg-blue-600 text-white px-6 py-3 rounded-xl hover:bg-blue-700 transition"
            onClick={() => handleConversion("txt-to-json", "archivo_convertido.json")}
          >
            Generar JSON
          </button>
          <button
            className="bg-green-600 text-white px-6 py-3 rounded-xl hover:bg-green-700 transition"
            onClick={() => handleConversion("txt-to-xml", "archivo_convertido.xml")}
          >
            Generar XML
          </button>
          <button
            className="bg-purple-600 text-white px-6 py-3 rounded-xl hover:bg-purple-700 transition"
            onClick={handleConvertToTxt}
          >
            Generar TXT
          </button>
        </div>

      </div>
    </div><ToastContainer
        position="top-center"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        transition={Slide} /></>

  );
}

export default App;
