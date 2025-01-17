import { useState, useEffect } from 'react';
import { FaPen } from 'react-icons/fa';

function CreateButton({
  onClick,
  isTopButtonVisible,
  containerClass = '',
  basePositionClass = '',
  icon, // 추가된 icon prop
}) {
  const [buttonPositionClass, setButtonPositionClass] =
    useState(basePositionClass);

  useEffect(() => {
    setButtonPositionClass(
      isTopButtonVisible
        ? 'bottom-[95px] right-[calc((100vw-375px)/2+16px)]'
        : 'bottom-10 right-[calc((100vw-375px)/2+16px)]',
    );
  }, [isTopButtonVisible]);

  return (
    <button
      onClick={onClick}
      className={`fixed bg-[var(--primary-color)] text-white w-12 h-12 rounded-full shadow-lg flex items-center justify-center z-9999 
        transition-all duration-300 ease-in-out transform 
        ${containerClass} ${buttonPositionClass}`}
      aria-label="Create new post"
    >
      {icon || <FaPen className="text-lg" />}
    </button>
  );
}

export default CreateButton;
