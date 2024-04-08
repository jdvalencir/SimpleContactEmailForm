import { Form } from "./components/Form"

function App() {

  return (
    <div className=" w-full flex flex-col justify-center items-center ">
      <div className="bg-form w-80 md:w-3/5 lg:w-1/3 flex flex-col items-center mt-4 md:pt-8 lg:pt-10 p-3" > 
        <img className="w-24 lg:w-32" src="https://revaisor.com/wp-content/uploads/2023/08/cropped-logo1-simple.png" alt="RevAIsor Logo" />
        <h1 className="lg:text-2xl font-bold mt-6">Contact Form RevAIsor</h1>
        <Form />
      </div>
    </div>
  )
}

export default App
